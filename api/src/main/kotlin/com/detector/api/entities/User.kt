package com.detector.api.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "user_info")
class User() {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null

    var name: String? = null

    var idDocument: String? = null

    @OneToOne(cascade = [CascadeType.PERSIST])
    var bankAccount: BankAccount? = null

}
