package com.detector.api.kafka.topic

import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaTopicConfig(
    @Value("\${kafka.bootstrap-server}")
    val bootstrapServer: String,

    @Value( "\${kafka.topics.transaction}" )
    val transactionTopic: String,

    @Value( "\${kafka.topics.threshold}" )
    val thresholdTopic: String

) {

    @Bean
    fun admin() = KafkaAdmin(mapOf(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServer))

    @Bean
    fun transactionTopic() =
        TopicBuilder.name(transactionTopic)
            .partitions(3)
            .replicas(2)
            .build()

    @Bean
    fun transactionThreshouldTopic() =
        TopicBuilder.name(thresholdTopic)
            .partitions(3)
            .replicas(2)
            .compact()
            .build()
}