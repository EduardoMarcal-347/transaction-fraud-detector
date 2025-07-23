package com.detector.processor.dto;

import com.detector.processor.enums.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class TransactionDTO {

    private Integer id;
    private Integer value;
    private String txLocation;
    private Instant createdAt;
    private Instant processedAt;
    private TransactionStatus status;
    private UserDTO payer;
    private UserDTO receiver;

    public TransactionDTO() {
    }

}
