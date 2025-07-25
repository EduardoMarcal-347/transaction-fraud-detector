package com.detector.api.dto.request

data class FinancialTransactionReq(
    val value: Double,
    val payer: PayerPartyReq,
    val receiver: ReceiverPartyReq,
)
