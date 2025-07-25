package com.detector.api.dto.request

import com.detector.api.enums.CountryCode

data class ReceiverPartyReq(
    val paymentInfo: PaymentInfoReq,
    override val transactionRole: String,
    override val location: CountryCode
): TransactionPartyReq()
