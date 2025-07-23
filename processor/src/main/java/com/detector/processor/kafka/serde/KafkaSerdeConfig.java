package com.detector.processor.kafka.serde;

import com.detector.processor.dto.ProcessedTxDTO;
import org.apache.kafka.common.serialization.Serde;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaSerdeConfig {

    @Bean
    public Serde<ProcessedTxDTO> processedTxSerde() {
        JsonSerde<ProcessedTxDTO> serde = new JsonSerde<>(ProcessedTxDTO.class);
        Map<String, Object> config = new HashMap<>();
        config.put(JsonSerializer.TYPE_MAPPINGS, "ProcessedTxDTO:com.detector.processor.dto.ProcessedTxDTO");
        serde.configure(config, false);
        return serde;
    }

}
