package com.detector.api.repositories

import com.detector.api.entities.FinancialTransaction
import com.detector.api.enums.TransactionStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface FinancialTransactionRepository : JpaRepository<FinancialTransaction, Int> {

    @Query("SELECT t FROM FinancialTransaction t WHERE t.payer = :payerId ORDER BY t.createdAt")
    fun findAllByPayerId(payerId: UUID) : List<FinancialTransaction>

    @Query("SELECT t FROM FinancialTransaction t WHERE t.payer = :payerId AND t.status = :status ORDER BY t.createdAt")
    fun findAllByPayerIdAndStatus(payerId: UUID, status: TransactionStatus) : List<FinancialTransaction>

}