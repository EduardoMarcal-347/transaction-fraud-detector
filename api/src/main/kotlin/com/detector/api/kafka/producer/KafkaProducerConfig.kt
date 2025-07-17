package com.detector.api.kafka.producer

import org.apache.kafka.clients.producer.ProducerConfig

import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfig {

    @Bean
    fun kafkaProducerFactory(): ProducerFactory<String?, Any?> {
        val properties = mutableMapOf<String?, Any?>()
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        properties.put(ProducerConfig.LINGER_MS_CONFIG, "20")
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, Integer.valueOf(32 * 1024).toString())
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy")
        return DefaultKafkaProducerFactory(properties)
    }

    @Bean fun kafkaTemplate(): KafkaTemplate<String?, Any?> {
        return KafkaTemplate<String?, Any?>(kafkaProducerFactory())
    }
}