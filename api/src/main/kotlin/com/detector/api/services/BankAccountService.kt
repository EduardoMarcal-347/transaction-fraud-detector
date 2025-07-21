package com.detector.api.services

import com.detector.api.dto.mapper.BankAccountMapper
import com.detector.api.dto.request.BankAccountReq
import com.detector.api.entities.BankAccount
import com.detector.api.repositories.BankAccountRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BankAccountService(
    val bankAccountRepository: BankAccountRepository
) {

    fun createBankAccount(req: BankAccount): BankAccount{
        return bankAccountRepository.save(req)
    }

    fun findBankAccount(id: UUID) : BankAccount{
        return bankAccountRepository.findById(id)
            .orElseThrow{ NoSuchElementException("Account doesn't exists") }
    }

}