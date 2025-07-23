package com.detector.processor.kafka.processor;

import com.detector.processor.dto.ThresholdDTO;
import com.detector.processor.dto.TransactionDTO;
import com.detector.processor.dto.ProcessedTxDTO;
import com.detector.processor.services.FraudDetectorService;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

@Component
public class TransactionProcessor {

    @Value("${kafka.topics.threshold}")
    private String thresholdTopic;

    @Value("${kafka.topics.processed-tx}")
    private String processedTxTopic;

    @Value("${kafka.topics.transaction}")
    private String transactionTopic;


    @Autowired
    private FraudDetectorService fraudDetectorService;

    @Bean
    public KStream<String, ProcessedTxDTO> processTransactions(
            StreamsBuilder builder,
            Serde<ProcessedTxDTO> responseSerde
    ){
        KStream<String, TransactionDTO> stream = builder.stream(transactionTopic,
                Consumed.with(Serdes.String(), new JsonSerde<>(TransactionDTO.class)));

        KTable<String, ThresholdDTO> thresholdTable = builder.table(thresholdTopic,
                Consumed.with(Serdes.String(), new JsonSerde<>(ThresholdDTO.class)));

        KStream<String, ProcessedTxDTO> streamResult = stream.leftJoin(thresholdTable,
                (tx, threshold) -> fraudDetectorService.processTransaction(tx, threshold),
                Joined.with(
                        Serdes.String(),
                        new JsonSerde<>(TransactionDTO.class),
                        new JsonSerde<>(ThresholdDTO.class)
                )
        );

        streamResult.to(processedTxTopic, Produced.with(
                Serdes.String(),
                responseSerde
        ));

        return streamResult;
    }
}
