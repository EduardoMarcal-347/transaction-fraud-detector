package com.detector.api.dto.request

data class UserReq(
    val name: String,
    val idDocument: String,
    val bankAccount: BankAccountReq
)
