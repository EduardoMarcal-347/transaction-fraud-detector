package com.detector.api.kafka.producer


import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaProducerConfig (
    @Value($$"${kafka.bootstrap-server}")
    val bootstrapServer: String
){

    @Bean
    fun kafkaProducerFactory(): ProducerFactory<String?, Any?> {
        val properties = mutableMapOf<String?, Any?>()
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer)
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer::class.java.name)
        properties.put(ProducerConfig.LINGER_MS_CONFIG, "20")
        properties.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false)
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, Integer.valueOf(32 * 1024).toString())
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy")
        return DefaultKafkaProducerFactory(properties)
    }

    @Bean fun kafkaTemplate(): KafkaTemplate<String?, Any?> {
        return KafkaTemplate<String?, Any?>(kafkaProducerFactory())
    }
}