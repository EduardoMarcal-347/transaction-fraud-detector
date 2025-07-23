package com.detector.processor.dto;

import com.detector.processor.enums.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessedTxDTO {

    private Integer id;
    private TransactionStatus status;

    public ProcessedTxDTO() {}

    public ProcessedTxDTO(Integer id, TransactionStatus status) {
        this.id = id;
        this.status = status;
    }
}
