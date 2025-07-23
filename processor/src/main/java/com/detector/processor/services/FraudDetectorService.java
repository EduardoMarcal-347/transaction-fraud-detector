package com.detector.processor.services;

import com.detector.processor.dto.ThresholdDTO;
import com.detector.processor.dto.TransactionDTO;
import com.detector.processor.dto.ProcessedTxDTO;
import com.detector.processor.enums.TransactionStatus;
import org.springframework.stereotype.Service;

@Service
public class FraudDetectorService {

    public ProcessedTxDTO processTransaction(TransactionDTO tx, ThresholdDTO threshold) {
        TransactionStatus txStatus = TransactionStatus.APPROVED;
        if (isTxSuspicious(tx, threshold)) {
            txStatus = TransactionStatus.SUSPICIOUS;
        }
        return new ProcessedTxDTO(tx.getId(), txStatus);
    }

    public boolean isTxSuspicious(TransactionDTO tx, ThresholdDTO threshold){
        return tx.getValue() > threshold.getMaxValue();
    }
}
