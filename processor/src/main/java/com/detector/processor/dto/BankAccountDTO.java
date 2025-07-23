package com.detector.processor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BankAccountDTO {

    private UUID id;
    private Integer agency;
    private String account;
    private Integer balance;
    private Object threshold;

    public BankAccountDTO() {
    }

}
