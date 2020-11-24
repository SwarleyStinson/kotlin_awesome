package ru.nspk.tpp.transaction.sgb.config

import io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS
import io.netty.handler.ssl.PemX509Certificate
import io.netty.handler.ssl.SslContext
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.apache.commons.io.IOUtils
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo
import org.bouncycastle.openssl.PEMParser
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import reactor.netty.Connection
import reactor.netty.tcp.TcpClient
import java.io.StringReader
import java.nio.file.Files
import java.nio.file.Path
import java.security.GeneralSecurityException
import java.security.KeyStore
import java.security.PrivateKey
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


@Configuration
open class SslConfig {
    companion object {
        private const val PRIVATE_KEY = "keystore/CLIENTkey.pem"
        private const val CERTIFICATE = "keystore/CLIENTcert.pem"
        private const val CA_CERTIFICATE = "keystore/CAcert.pem"

        fun createSslContext(): SslContext {
            return SslContextBuilder.forClient()
                    .keyManager(
                            ClassPathResource(CERTIFICATE).file,
                            ClassPathResource(PRIVATE_KEY).file
                    )
//                .trustManager(getTrustManagerFactory())
                    .trustManager(trustManagerForCertificates())
                    .build()
        }

        fun createSSLContext(): SSLContext = SSLContext.getInstance("TLSv1.2").apply {
            init(getKeyManagers(), getTrustManagers(), null)
        }

    fun createSSLContext2(): SSLContext = SSLContext.getInstance("TLSv1.2").apply {
        init(getKeyManagers(), arrayOf<TrustManager>(trustManagerForCertificates()), null)
    }

        fun getKeyManagers(): Array<out KeyManager>? {
            val caCert = getCertificate(CA_CERTIFICATE)
            val cert = getCertificate(CERTIFICATE)

            val factory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())

            val keyStore = KeyStore.getInstance("JKS")
            keyStore.load(null, null)
            keyStore.setCertificateEntry("ca-cert", caCert)
            keyStore.setCertificateEntry("client-cert", cert)
            keyStore.setKeyEntry("client-key", loadPrivateKey(), "1234".toCharArray(), arrayOf<Certificate>(cert))
            factory.init(keyStore, "1234".toCharArray())

            return factory.keyManagers
        }

        private fun loadPrivateKey(): PrivateKey {
            val pemFile = ClassPathResource(PRIVATE_KEY)
            val key = String(Files.readAllBytes(Path.of(pemFile.uri)))
            val parser = PEMParser(StringReader(key))
            val privateKeyInfo = parser.readObject() as PrivateKeyInfo
            return JcaPEMKeyConverter().getPrivateKey(privateKeyInfo)
        }

        private fun getCertificate(file: String): Certificate {
            return CertificateFactory.getInstance("X509").generateCertificate(ClassPathResource(file).inputStream)
        }

        fun getTrustManagers(): Array<out TrustManager>? {
            return getTrustManagerFactory().trustManagers
        }

        fun getTrustManagerFactory(): TrustManagerFactory {
            val caKeyStore: KeyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            caKeyStore.load(null, null)
            caKeyStore.setCertificateEntry("ca-certificate", PemX509Certificate.valueOf(IOUtils.toByteArray(ClassPathResource(CA_CERTIFICATE).inputStream)))

            val trustManagerFactory: TrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(caKeyStore)

            return trustManagerFactory
        }

        @Throws(GeneralSecurityException::class)
        private fun trustManagerForCertificates(): X509TrustManager {
            var x509Tm: X509TrustManager? = null
            TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()).apply {
                init(null as KeyStore?)
                for (tm in this.trustManagers) {
                    if (tm is X509TrustManager) {
                        x509Tm = tm
                        break
                    }
                }
            }
            return object : X509TrustManager {
                //            override fun getAcceptedIssuers() = x509Tm!!.acceptedIssuers
                override fun getAcceptedIssuers() = arrayOf<X509Certificate>(getCertificate(CA_CERTIFICATE) as X509Certificate)

                override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {}

                override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {}
            }
        }
    }

//    @Bean
    fun tcpClient(): TcpClient {
        var tcpClient = TcpClient.create()
                .option(CONNECT_TIMEOUT_MILLIS, 60000)
                .doOnConnected { connection: Connection ->
                    connection.addHandlerLast(ReadTimeoutHandler(60000, TimeUnit.MILLISECONDS))
                    connection.addHandlerLast(WriteTimeoutHandler(60000, TimeUnit.MILLISECONDS))
                }
//                .proxy { spec: ProxyProvider.TypeSpec ->
        /* dev version */
//                    spec.type(SOCKS4).host("cp-proxy.nspk.ru").port(8080)
//                    spec.type(SOCKS4).host("msk1-vm-proxy02.unix.nspk.ru").port(3128)
//                }

        /* SSL attempt 1: netty */
//        println("\n\nsslcontext by netty\n")
//        tcpClient = tcpClient.secure { spec: SslProvider.SslContextSpec ->
//            spec.sslContext(createSslContext())
//        }

        /* SSL attempt 2: jdk */
//        println("\n\nsslcontext by jdk\n")
//        tcpClient = tcpClient.secure { spec: SslProvider.SslContextSpec ->
//            spec.sslContext(JdkSslContext(createSSLContext(), true, ClientAuth.REQUIRE))
//        }

        return tcpClient
    }



}