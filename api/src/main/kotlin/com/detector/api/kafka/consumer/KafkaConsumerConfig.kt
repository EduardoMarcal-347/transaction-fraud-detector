package com.detector.api.kafka.consumer

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer


@Configuration
class KafkaConsumerConfig(
    @Value($$"${kafka.bootstrap-server}")
    val bootstrapServer: String,
    @Value($$"${kafka.consumer.group-id}")
    val groupId: String
) {

    @Bean
    fun kafkaConsumerFactory(): ConsumerFactory<String?, Any?> {
        val properties = mutableMapOf<String?, Any?>()
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer)
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer::class.java.name)
        properties.put(JsonDeserializer.TYPE_MAPPINGS, "ProcessedTxDTO:com.detector.api.dto.message.ProcessedTxDTO")
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
        return DefaultKafkaConsumerFactory(properties)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any?> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, Any?> =
            ConcurrentKafkaListenerContainerFactory<String, Any?>()
        factory.consumerFactory = kafkaConsumerFactory()

        return factory
    }

}