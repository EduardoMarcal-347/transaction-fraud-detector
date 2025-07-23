package com.detector.api.entities

import com.detector.api.enums.CountryCode
import com.detector.api.enums.TransactionStatus
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "financial_transaction")
class FinancialTransaction() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    var value: Int? = null

    var txLocation: CountryCode? = CountryCode.BR

    var createdAt: Instant? = Instant.now()

    var processedAt: Instant? = null

    @Enumerated(value = EnumType.STRING)
    var status: TransactionStatus? = TransactionStatus.ONGOING

    @ManyToOne
    var payer: User? = null

    @ManyToOne
    var receiver: User? = null
}
