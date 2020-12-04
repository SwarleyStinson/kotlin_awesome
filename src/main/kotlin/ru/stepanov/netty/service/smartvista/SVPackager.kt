package ru.stepanov.netty.service.smartvista

import org.jpos.iso.IFA_AMOUNT
import org.jpos.iso.IFA_BINARY
import org.jpos.iso.IFA_LLBINARY
import org.jpos.iso.IFA_LLCHAR
import org.jpos.iso.IFA_LLLBINARY
import org.jpos.iso.IFA_LLLCHAR
import org.jpos.iso.IFA_LLNUM
import org.jpos.iso.IFA_NUMERIC
import org.jpos.iso.IFB_AMOUNT
import org.jpos.iso.IFB_BINARY
import org.jpos.iso.IFB_BITMAP
import org.jpos.iso.IFB_LLCHAR
import org.jpos.iso.IFB_LLLBINARY
import org.jpos.iso.IFB_LLLCHAR
import org.jpos.iso.IFB_LLNUM
import org.jpos.iso.IFB_NUMERIC
import org.jpos.iso.IF_CHAR
import org.jpos.iso.ISOBasePackager
import org.jpos.iso.ISOFieldPackager

/**
 * ISO 8583 v1987 BINARY Packager
 * customized for Openway 8583
 */
class SVPackager : ISOBasePackager() {

    var fields: Array<out ISOFieldPackager> = arrayOf(
        IFA_NUMERIC(4, "MESSAGE TYPE INDICATOR"),  /*000 SV IFB_NUMERIC*/
        IFB_BITMAP(16, "BIT MAP"),  /*001*/
        IFA_LLNUM(19, "PAN - PRIMARY ACCOUNT NUMBER"),  /*002*/
        IFA_NUMERIC(6, "PROCESSING CODE"),  /*003*/
        IFA_NUMERIC(12, "AMOUNT, TRANSACTION"),  /*004*/
        IFA_NUMERIC(12, "AMOUNT, SETTLEMENT"),  /*005*/
        IFA_NUMERIC(12, "AMOUNT, CARDHOLDER BILLING"),  /*006*/
        IFA_NUMERIC(10, "TRANSMISSION DATE AND TIME"),  /*007*/
        IFA_NUMERIC(8, "AMOUNT, CARDHOLDER BILLING FEE"),  /*008*/
        IFA_NUMERIC(8, "CONVERSION RATE, SETTLEMENT"),  /*009*/
        IFA_NUMERIC(8, "CONVERSION RATE, CARDHOLDER BILLING"),  /*010*/
        IFA_NUMERIC(6, "SYSTEM TRACE AUDIT NUMBER"),  /*011*/
        IFA_NUMERIC(12, "TIME, LOCAL TRANSACTION"),  /*012 SV*/
        IFA_NUMERIC(4, "DATE, LOCAL TRANSACTION"),  /*013*/
        IFA_NUMERIC(6, "DATE, EXPIRATION"),  /*014 SV*/
        IFA_NUMERIC(6, "DATE, SETTLEMENT"),  /*015 Utemov 4->6*/
        IFA_NUMERIC(4, "DATE, CONVERSION"),  /*016*/
        IFA_NUMERIC(4, "DATE, CAPTURE"),  /*017*/
        IFA_NUMERIC(4, "MERCHANTS TYPE"),  /*018*/
        IFA_NUMERIC(3, "ACQUIRING INSTITUTION COUNTRY CODE"),  /*019*/
        IFA_NUMERIC(3, "PAN EXTENDED COUNTRY CODE"),  /*020*/
        IFA_NUMERIC(3, "FORWARDING INSTITUTION COUNTRY CODE"),  /*021*/
        IFA_NUMERIC(3, "POINT OF SERVICE ENTRY MODE"),  /*022*/
        IFA_NUMERIC(3, "CARD SEQUENCE NUMBER"),  /*023*/
        IFA_NUMERIC(3, "FUNCTION CODE"),  /*024 Utemov NETWORK INTERNATIONAL IDENTIFIEER -> FUNCTION CODE*/
        IFA_NUMERIC(2, "POINT OF SERVICE CONDITION CODE"),  /*025*/
        IFA_NUMERIC(2, "POINT OF SERVICE PIN CAPTURE CODE"),  /*026*/
        IFA_NUMERIC(1, "AUTHORIZATION IDENTIFICATION RESP LEN"),  /*027*/
        IFA_AMOUNT(9, "AMOUNT, TRANSACTION FEE"),  /*028*/
        IFA_AMOUNT(3, "RECONCILIATION INDICATOR"),  /*029 Utemov 9->3 AMOUNT, SETTLEMENT FEE -> RECONCILIATION INDICATOR*/
        IFA_AMOUNT(24, "AMOUNTS ORIGINAL"),  /*030 Utemov 9->24 AMOUNT, TRANSACTION PROCESSING FEE -> AMOUNTS ORIGINAL*/
        IFA_LLLCHAR(999, "SECURITY ADDITIONAL DATA – PRIVATE"),  /*031 Utemov 9->999 IFB_AMOUNT->IFB_LLLCHAR AMOUNT, SETTLEMENT PROCESSING FEE -> SECURITY ADDITIONAL DATA – PRIVATE*/
        IFA_LLNUM(11, "ACQUIRING INSTITUTION IDENT CODE"),  /*032*/
        IFA_LLNUM(11, "FORWARDING INSTITUTION IDENT CODE"),  /*033*/
        IFA_LLCHAR(28, "PAN EXTENDED"),  /*034*/
        IFA_LLCHAR(37, "TRACK 2 DATA"),  /*035 Utemov IFB_LLNUM->IFB_LLCHAR*/
        IFA_LLLCHAR(104, "TRACK 3 DATA"),  /*036*/
        IF_CHAR(12, "RETRIEVAL REFERENCE NUMBER"),  /*037*/
        IF_CHAR(6, "AUTHORIZATION IDENTIFICATION RESPONSE"),  /*038*/
        IF_CHAR(3, "RESPONSE CODE"),  /*039 SV*/
        IF_CHAR(3, "SERVICE RESTRICTION CODE"),  /*040*/
        IF_CHAR(8, "CARD ACCEPTOR TERMINAL IDENTIFICACION"),  /*041*/
        IF_CHAR(15, "CARD ACCEPTOR IDENTIFICATION CODE"),  /*042*/
        IF_CHAR(40, "CARD ACCEPTOR NAME/LOCATION"),  /*043*/
        IFA_LLCHAR(99, "ADITIONAL RESPONSE DATA"),  /*044 Utemov 25->99*/
        IFA_LLCHAR(76, "TRACK 1 DATA"),  /*045*/
        IFA_LLLCHAR(999, "ADITIONAL DATA - ISO"),  /*046*/
        IFA_LLLCHAR(999, "ADITIONAL DATA - NATIONAL"),  /*047*/
        IFA_LLLCHAR(999, "ADITIONAL DATA - PRIVATE"),  /*048*/
        IFA_NUMERIC(3, "CURRENCY CODE, TRANSACTION"),  /*049*/
        IF_CHAR(3, "CURRENCY CODE, SETTLEMENT"),  /*050*/
        IF_CHAR(3, "CURRENCY CODE, CARDHOLDER BILLING"),  /*051*/
        IFB_BINARY(8, "PIN DATA"),  /*052*/
        IFA_LLBINARY(16, "SECURITY RELATED CONTROL INFORMATION"),  /*053 SV*/
        IFA_LLLCHAR(120, "ADDITIONAL AMOUNTS"),  /*054*/
        IFB_LLLBINARY(999, "ICC DATA"),  /*055*/
        IFA_LLNUM(35, "ORIGINAL DATA ELEMENTS"),  /*056 Utemov 999->35 IFB_LLLCHAR->IFB_LLNUM Utemov RESERVED ISO->ORIGINAL DATA ELEMENTS*/
        IF_CHAR(3, "AUTHORISATION LIFE CYCLE CODE"),  /*057 Utemov 999->3 IFB_LLLCHAR->IF_CHAR RESERVED NATIONAL->AUTHORISATION LIFE CYCLE CODE*/
        IFA_LLNUM(11, "AUTHORIZING AGENT INSTITUTION ID"),  /*058 Utemov 999->11 IFB_LLLCHAR->IFB_LLNUM RESERVED NATIONAL->AUTHORIZING AGENT INSTITUTION ID*/
        IFA_LLLBINARY(999, "ADDITIONAL DATA"),  /*059 Utemov IFB_LLLCHAR->IFB_LLLBINARY RESERVED NATIONAL->ADDITIONAL DATA*/
        IFA_LLLCHAR(999, "ORIGINAL DATA ELEMENTS"),  /*060 Utemov RESERVED PRIVATE->ORIGINAL DATA ELEMENTS*/
        IFA_LLLCHAR(999, "RESERVED PRIVATE"),  /*061*/
        IFA_LLLCHAR(999, "RESERVED PRIVATE"),  /*062*/
        IFA_LLLCHAR(999, "ADDITIONAL DATA"),  /*063 Utemov RESERVED PRIVATE->ADDITIONAL DATA*/
        IFA_BINARY(4, "MESSAGE AUTHENTICATION CODE FIELD"),  /*064 Utemov 8->4*/
        IFA_BINARY(1, "BITMAP, EXTENDED"),  /*065*/
        IFA_NUMERIC(1, "SETTLEMENT CODE"),
        IFA_NUMERIC(2, "EXTENDED PAYMENT CODE"),
        IFA_NUMERIC(3, "RECEIVING INSTITUTION COUNTRY CODE"),
        IFA_NUMERIC(3, "SETTLEMENT INSTITUTION COUNTRY CODE"),
        IFB_NUMERIC(3, "NETWORK MANAGEMENT INFORMATION CODE", true),
        IFB_NUMERIC(4, "MESSAGE NUMBER", true),
        IFB_NUMERIC(4, "MESSAGE NUMBER LAST", true),
        IFB_NUMERIC(6, "DATE ACTION", true),
        IFB_NUMERIC(10, "CREDITS NUMBER", true),
        IFB_NUMERIC(10, "CREDITS REVERSAL NUMBER", true),
        IFB_NUMERIC(10, "DEBITS NUMBER", true),
        IFB_NUMERIC(10, "DEBITS REVERSAL NUMBER", true),
        IFB_NUMERIC(10, "TRANSFER NUMBER", true),
        IFB_NUMERIC(10, "TRANSFER REVERSAL NUMBER", true),
        IFB_NUMERIC(10, "INQUIRIES NUMBER", true),
        IFB_NUMERIC(10, "AUTHORIZATION NUMBER", true),
        IFB_NUMERIC(12, "CREDITS, PROCESSING FEE AMOUNT", true),
        IFB_NUMERIC(12, "CREDITS, TRANSACTION FEE AMOUNT", true),
        IFB_NUMERIC(12, "DEBITS, PROCESSING FEE AMOUNT", true),
        IFB_NUMERIC(12, "DEBITS, TRANSACTION FEE AMOUNT", true),
        IFB_NUMERIC(16, "CREDITS, AMOUNT", true),
        IFB_NUMERIC(16, "CREDITS, REVERSAL AMOUNT", true),
        IFB_NUMERIC(16, "DEBITS, AMOUNT", true),
        IFB_NUMERIC(16, "DEBITS, REVERSAL AMOUNT", true),
        IFB_NUMERIC(42, "ORIGINAL DATA ELEMENTS", true),
        IF_CHAR(1, "FILE UPDATE CODE"),
        IF_CHAR(2, "FILE SECURITY CODE"),
        IF_CHAR(6, "RESPONSE INDICATOR"),
        IF_CHAR(7, "SERVICE INDICATOR"),
        IF_CHAR(42, "REPLACEMENT AMOUNTS"),
        IFB_BINARY(16, "MESSAGE SECURITY CODE"),
        IFB_AMOUNT(17, "AMOUNT, NET SETTLEMENT", pad),
        IF_CHAR(25, "PAYEE"),
        IFB_LLNUM(11, "SETTLEMENT INSTITUTION IDENT CODE", pad),
        IFB_LLNUM(11, "RECEIVING INSTITUTION IDENT CODE", pad),
        IFB_LLCHAR(17, "FILE NAME"),
        IFB_LLCHAR(28, "ACCOUNT IDENTIFICATION 1"),
        IFB_LLCHAR(28, "ACCOUNT IDENTIFICATION 2"),
        IFB_LLLCHAR(100, "TRANSACTION DESCRIPTION"),
        IFB_LLLCHAR(999, "RESERVED ISO USE"),
        IFB_LLLCHAR(999, "RESERVED ISO USE"),
        IFB_LLLCHAR(999, "RESERVED ISO USE"),
        IFB_LLLCHAR(999, "RESERVED ISO USE"),
        IFB_LLLCHAR(999, "RESERVED ISO USE"),
        IFB_LLLCHAR(999, "RESERVED ISO USE"),
        IFB_LLLCHAR(999, "RESERVED ISO USE"),
        IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
        IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
        IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
        IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
        IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
        IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
        IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
        IFB_LLLCHAR(999, "RESERVED NATIONAL USE"),
        IFB_LLLCHAR(999, "RESERVED PRIVATE USE"),
        IFB_LLLCHAR(999, "RESERVED PRIVATE USE"),
        IFB_LLLCHAR(999, "RESERVED PRIVATE USE"),
        IFB_LLLCHAR(999, "RESERVED PRIVATE USE"),
        IFB_LLLCHAR(999, "RESERVED PRIVATE USE"),
        IFB_LLLCHAR(999, "RESERVED PRIVATE USE"),
        IFB_LLLCHAR(999, "RESERVED PRIVATE USE"),
        IFB_LLLCHAR(999, "RESERVED PRIVATE USE"),
        IFB_BINARY(8, "MAC 2")
    )

    init {
        setFieldPackager(fields)
    }

    companion object {
        private const val pad = false
    }
}