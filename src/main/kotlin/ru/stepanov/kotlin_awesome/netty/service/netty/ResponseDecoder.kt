package ru.stepanov.kotlin_awesome.netty.service.netty

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ReplayingDecoder
import org.jpos.iso.ISOMsg
import ru.stepanov.kotlin_awesome.netty.service.smartvista.SVPackager


class ResponseDecoder : ReplayingDecoder<ISOMsg?>() {

    @Throws(Exception::class)
    override fun decode(channelHandlerContext: ChannelHandlerContext?, serverIn: ByteBuf, out: MutableList<Any?>) {
        val m = ISOMsg()
        m.packager = SVPackager().apply { headerLength = 4 }
        m.direction = ISOMsg.INCOMING
        kotlin.runCatching {
            serverIn.markReaderIndex()
            val bheader = ByteArray(4)
            serverIn.readBytes(bheader)
            val outputLen = String(bheader).toInt()
            val bm = ByteArray(outputLen + 4)
            serverIn.resetReaderIndex()
            serverIn.readBytes(bm)
            m.unpack(bm)
            out.add(m)
        }.onFailure { exception ->
            val msgex = exception.message
            println("\n\nresult: EXCEPTION when decoding - $msgex\n")
            m.dump(System.out, "")
            exception.printStackTrace()
        }.onSuccess {
            logISOMsg(m)
        }
    }

    @Throws(Exception::class)
    private fun logISOMsg(msg: ISOMsg) {
        println("----INCOMING JPOS-----")
        println("  MTI : " + String.format("%04d", msg.mti.toInt()))
        for (i in 1..msg.maxField) {
            if (msg.hasField(i)) {
                println("    Field-" + i + " : " + msg.getString(i))
            }
        }
        println("--------------------")
    }
}