package com.detector.api.entities

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "tx_threshold")
data class Threshold(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Int? = null,

    val maxValue: Int,

    val dailyLimit: Int,

)
