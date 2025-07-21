package com.detector.api.dto.mapper

import com.detector.api.dto.request.BankAccountReq
import com.detector.api.entities.BankAccount
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper(uses = [ThresholdMapper::class])
interface BankAccountMapper {
    fun toBean(requestDTO: BankAccountReq): BankAccount

    companion object {
        val INSTANCE: BankAccountMapper = Mappers.getMapper(BankAccountMapper::class.java)
    }
}