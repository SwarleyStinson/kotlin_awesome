package ru.nspk.tpp.transaction.sgb.service.impl

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
//import reactor.kafka.receiver.ReceiverRecord
//import ru.nspk.tpp.transaction.sgb.processor.model.bank.EchoMessage
//import ru.nspk.tpp.transaction.sgb.processor.model.bank.TransactionMessage
import ru.nspk.tpp.transaction.sgb.service.AuthorizationService
import ru.nspk.tpp.transaction.sgb.service.netty.NettyClient
import javax.annotation.PostConstruct

@Service
class SgbAuthorizationServiceImpl(
        val client: NettyClient
) : AuthorizationService {

    companion object {
        private val log = LoggerFactory.getLogger(SgbAuthorizationServiceImpl::class.java)
    }

//    override fun process(record: ReceiverRecord<String, ByteArray>) {}

//    override fun sendEcho(echoMessage: EchoMessage) {}

//    override fun send(transactionMessage: TransactionMessage) {}

    @PostConstruct
    fun init() {
        client.run()
    }

}

