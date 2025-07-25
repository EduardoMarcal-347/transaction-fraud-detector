package com.detector.api.dto.request

data class BankAccountReq(
    val agency : Int,
    val account: String,
    val balance: Int,
    val threshold: ThresholdReq
) : PaymentInfoReq()
