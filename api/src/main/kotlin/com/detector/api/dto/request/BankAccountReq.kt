package com.detector.api.dto.request

import com.detector.api.entities.Threshold

data class BankAccountReq(
    val agency : Int,
    val account: String,
    val balance: Int,
    val threshold: ThresholdReq
)
