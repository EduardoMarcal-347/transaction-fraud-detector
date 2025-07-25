package com.detector.api.services

import com.detector.api.dto.mapper.FinancialTransactionMapper
import com.detector.api.dto.message.ProcessedTxDTO
import com.detector.api.dto.request.FinancialTransactionReq
import com.detector.api.dto.request.PayerPartyReq
import com.detector.api.entities.FinancialTransaction
import com.detector.api.enums.TransactionStatus
import com.detector.api.repositories.FinancialTransactionRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class FinancialTransactionService(
    @Value($$"${kafka.topics.transaction}")
    val transactionTopic: String,
    val kafkaTemplate: KafkaTemplate<String, Any>,
    val transactionRepository: FinancialTransactionRepository,
    val userService: UserService
) {

    fun createTransaction(req: FinancialTransactionReq): FinancialTransaction {
        assertPayerExists(req.payer)
        val transaction = FinancialTransactionMapper.INSTANCE.toBean(req)

        val entity = transactionRepository.save(transaction)
        kafkaTemplate.send(transactionTopic, entity.payer!!.id.toString(), entity)
        return entity
    }

    fun getTransactionsHistory(payerId: UUID): List<FinancialTransaction> {
        return transactionRepository.findAllByPayerId(payerId)
    }

    fun getTransactionHistoryByStatus(payerId: UUID, status: TransactionStatus): List<FinancialTransaction> {
        return transactionRepository.findAllByPayerIdAndStatus(payerId, status)
    }

    fun findTransaction(transactionId: Int): FinancialTransaction {
        return transactionRepository.findById(transactionId)
            .orElseThrow { NoSuchElementException("Unknown Transaction with id $transactionId") }
    }

    fun updateProcessedTransaction(processedTx: ProcessedTxDTO) {
        val tx = findTransaction(processedTx.id)
        tx.status = processedTx.status
        tx.processedAt = Instant.now()
        transactionRepository.save(tx)
    }

    fun assertPayerExists(payer: PayerPartyReq){
        if ( !userService.userExists(payer.userId) ) {
            throw NoSuchElementException("Payer with id ${payer.userId} doesn't exist.")
        }
    }

}