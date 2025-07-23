package com.detector.api.dto.message

import com.detector.api.enums.TransactionStatus

data class ProcessedTxDTO(
    var id : Int,
    var status: TransactionStatus
)
