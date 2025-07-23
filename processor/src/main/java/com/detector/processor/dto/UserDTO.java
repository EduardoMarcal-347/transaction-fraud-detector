package com.detector.processor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDTO {

    private UUID id;
    private String name;
    private String idDocument;
    private BankAccountDTO bankAccount;

    public UserDTO() {
    }

}
