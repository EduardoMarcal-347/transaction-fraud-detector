package com.detector.api.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "bank_account")
class BankAccount() {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id : UUID? = null
    var agency : Int? = null
    var account: String? = null
    var balance: Int? = 0

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "threshold_id")
    var threshold: Threshold? = null
}
