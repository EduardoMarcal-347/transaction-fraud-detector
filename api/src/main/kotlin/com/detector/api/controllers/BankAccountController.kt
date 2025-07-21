package com.detector.api.controllers

import com.detector.api.services.BankAccountService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@Controller
@RequestMapping("/api/bank-account/")
class BankAccountController(
    val bankAccountService: BankAccountService
) {

    @GetMapping("{id}")
    fun findBankAccount(@PathVariable accountId : UUID) : ResponseEntity<Any>{
        return ResponseEntity.ok(bankAccountService.findBankAccount(accountId))
    }


}