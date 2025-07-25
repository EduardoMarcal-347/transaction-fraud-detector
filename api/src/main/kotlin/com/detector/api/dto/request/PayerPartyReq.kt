package com.detector.api.dto.request

import com.detector.api.enums.CountryCode
import java.util.UUID

data class PayerPartyReq(
    val userId: UUID,
    override val transactionRole: String,
    override val location: CountryCode
) : TransactionPartyReq()
