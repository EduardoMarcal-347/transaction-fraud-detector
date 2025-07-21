package com.detector.api.controllers

import com.detector.api.dto.request.FinancialTransactionReq
import com.detector.api.enums.TransactionStatus
import com.detector.api.services.FinancialTransactionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@Controller
@RequestMapping("/api/transactions/")
class FinancialTransactionController(
    val financialTransactionService: FinancialTransactionService
) {

    @PostMapping
    fun create(@RequestBody req: FinancialTransactionReq) : ResponseEntity<Any?>{
        val transaction = financialTransactionService.createTransaction(req)
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction)
    }

    @GetMapping("history/{payerId}")
    fun getTransactionsHistory(@PathVariable payerId: UUID) {
        financialTransactionService.getTransactionsHistory(payerId)
    }

    @GetMapping("history/{payerId}/{status}")
    fun getTransactionsHistoryByStatus(@PathVariable payerId: UUID, @PathVariable status: TransactionStatus) {
        financialTransactionService.getTransactionHistoryByStatus(payerId, status)
    }

    @GetMapping("id/{id}")
    fun findTransaction(@PathVariable transactionId: Int) {
        financialTransactionService.findTransaction(transactionId)
    }

}