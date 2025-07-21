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
data class BankAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id : UUID? = null,
    val agency : Int,
    val account: String,
    val balance: Int,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "threshold_id")
    val threshold: Threshold? = null
)
