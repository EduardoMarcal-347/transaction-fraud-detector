package com.detector.api.entities

import com.detector.api.enums.CountryCode
import com.detector.api.enums.TransactionStatus
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "financial_transaction")
data class FinancialTransaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null,

    var value: Int,

    var txLocation: CountryCode,

    val createdAt: Instant? = Instant.now(),

    val processedAt: Instant? = null,

    @Enumerated(value = EnumType.STRING)
    val status: TransactionStatus? = TransactionStatus.ONGOING,

    @ManyToOne
    val payer: User,

    @ManyToOne
    val receiver: User
)
