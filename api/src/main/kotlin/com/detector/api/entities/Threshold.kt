package com.detector.api.entities

import jakarta.persistence.*

@Entity
@Table(name = "tx_threshold")
class Threshold() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var maxValue: Int? = null

    var dailyLimit: Int? = null

}
