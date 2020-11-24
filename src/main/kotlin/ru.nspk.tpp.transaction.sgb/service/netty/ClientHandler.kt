package ru.nspk.tpp.transaction.sgb.service.netty

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import org.jpos.iso.ISOMsg
import org.springframework.stereotype.Service
import ru.nspk.tpp.transaction.sgb.service.smartvista.SVPackager
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule


@Service
class ClientHandler : ChannelInboundHandlerAdapter() {
    private var ctx: ChannelHandlerContext? = null
    @Throws(java.lang.Exception::class)
    override fun channelActive(ctx: ChannelHandlerContext) {
        this.ctx = ctx

        Timer().schedule(0, TimeUnit.SECONDS.toMillis(55)) {
            val echoIsoMsg = ISOMsg().apply {
                packager = SVPackager().apply { headerLength = 4 }
                direction = ISOMsg.OUTGOING
                mti = "0800"
                set(3, "990000")
                set(7, LocalDateTime.now(ZoneId.of("UTC")).format(DateTimeFormatter.ofPattern("MMddHHmmss")))
                set(11, "000002")
                set(24, "831")
                set(41, "00300777")
                set(42, "9003412")
            }
            ctx.writeAndFlush(echoIsoMsg)
        }
    }

    @Throws(java.lang.Exception::class)
    override fun channelInactive(ctx: ChannelHandlerContext) {
        var ctx: ChannelHandlerContext? = ctx
        ctx = null
    }

    @Throws(java.lang.Exception::class)
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        var echoIsoMsg810 = msg as ISOMsg
        if (echoIsoMsg810 == null) return

        when {
            echoIsoMsg810.hasField(39) &&
                    (echoIsoMsg810.getString(39).equals("000") ||
                            echoIsoMsg810.getString(39).equals("003"))
            -> {
                println("\n\nstatus Success\n")
            }
            else -> {
                println("\n\nstatus Fail\n")
            }
        }
    }


}