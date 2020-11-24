package ru.nspk.tpp.transaction.sgb.service.netty

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.ssl.ClientAuth
import io.netty.handler.ssl.JdkSslContext
import org.springframework.stereotype.Service
import ru.nspk.tpp.transaction.sgb.config.SslConfig

@Service
class NettyClient {
    companion object {
        private const val HOST = "194.186.151.174"
        private const val PORT = 6688
//        private const val HOST = "msk1-lkofedev02.unix.nspk.ru"
//        private const val PORT = 6688
    }

    @Throws(Exception::class)
    fun run() {
        val workerGroup: EventLoopGroup = NioEventLoopGroup() as EventLoopGroup
        try {
            val b = Bootstrap()
            b.group(workerGroup)
            b.channel(NioSocketChannel::class.java)
            b.option(ChannelOption.SO_KEEPALIVE, true)

            b.handler(object : ChannelInitializer<SocketChannel>() {
                @Throws(Exception::class)
                override fun initChannel(ch: SocketChannel) {
                    val sslContext = JdkSslContext(SslConfig.createSSLContext2(), true, ClientAuth.REQUIRE)
                    ch.pipeline().addLast(sslContext.newHandler(ch.alloc(), HOST, PORT))

                    ch.pipeline().addLast(RequestEncoder(), ResponseDecoder(), ClientHandler())
                }
            })

//            val f: ChannelFuture = b.connect(commonProperties.proxy.host, commonProperties.proxy.port).sync()
            val f: ChannelFuture = b.connect(HOST, PORT).sync()

            f.channel().closeFuture().sync()
        } catch (e: Exception) {
            println(1)
        } finally {
            workerGroup.shutdownGracefully()
        }
    }

}
