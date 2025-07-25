package com.detector.api.dto.request

import com.detector.api.enums.CountryCode

abstract class TransactionPartyReq {
    abstract val transactionRole: String
    abstract val location: CountryCode
}