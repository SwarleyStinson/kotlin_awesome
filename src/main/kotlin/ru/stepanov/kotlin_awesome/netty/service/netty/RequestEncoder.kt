package ru.stepanov.kotlin_awesome.netty.service.netty

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder
import org.jpos.iso.ISOMsg

class RequestEncoder : MessageToByteEncoder<ISOMsg>() {
    @Throws(Exception::class)
    override fun encode(channelHandlerContext: ChannelHandlerContext?, msg: ISOMsg, serverOut: ByteBuf) {
        logISOMsg(msg)

        kotlin.runCatching {
            val b = msg.pack()
            val len = b.size
            msg.header = len.toString().padStart(4, '0').toByteArray()
        }.onFailure { exception ->
            val message = exception.message
            println("\n\nresult: EXCEPTION when encoding - $message\n")
            msg.dump(System.out, "")
            exception.printStackTrace()
        }.onSuccess {
            serverOut.writeBytes(msg.pack())
        }
    }

    @Throws(Exception::class)
    private fun logISOMsg(msg: ISOMsg) {
        println("----OUTGOING JPOS-----")
        println("  MTI : " + String.format("%04d", msg.mti.toInt()))
            for (i in 1..msg.maxField) {
                if (msg.hasField(i)) {
                    println("    Field-" + i + " : " + msg.getString(i))
                }
            }
        println("--------------------")
    }

}