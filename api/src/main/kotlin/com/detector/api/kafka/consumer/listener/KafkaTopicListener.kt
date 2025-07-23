package com.detector.api.kafka.consumer.listener

import com.detector.api.dto.message.ProcessedTxDTO
import com.detector.api.services.FinancialTransactionService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class KafkaTopicListener(
    val transactionService: FinancialTransactionService
) {

    @KafkaListener(
        topics = [$$"${kafka.topics.processed-tx}"],
        groupId = $$"${kafka.consumer.group-id}",
        containerFactory = "kafkaListenerContainerFactory",
        concurrency = "3"
    )
    fun transactionListener(@Payload data: ProcessedTxDTO) {
        transactionService.updateProcessedTransaction(data)
    }

}