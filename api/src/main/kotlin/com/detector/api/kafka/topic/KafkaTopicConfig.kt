package com.detector.api.kafka.topic

import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaTopicConfig {

    @Bean
    fun admin() = KafkaAdmin(mapOf(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to "localhost:9092"))

    @Bean
    fun transactionTopic() =
        TopicBuilder.name("user.transaction.queue")
            .partitions(3)
            .replicas(2)
            .compact()
            .build()

    @Bean
    fun transactionThreshouldTopic() =
        TopicBuilder.name("user.transaction.threshold")
            .partitions(3)
            .replicas(2)
            .compact()
            .build()

    @Bean
    fun suspiciousTransactionTopic() =
        TopicBuilder.name("user.transaction.suspicious")
            .partitions(3)
            .replicas(2)
            .compact()
            .build()

}