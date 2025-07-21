package com.detector.api.repositories

import com.detector.api.entities.BankAccount
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BankAccountRepository : JpaRepository<BankAccount, UUID>{
}