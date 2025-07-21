package com.detector.api.dto.mapper

import com.detector.api.dto.request.FinancialTransactionReq
import com.detector.api.entities.FinancialTransaction
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.NullValuePropertyMappingStrategy
import org.mapstruct.factory.Mappers

@Mapper(
    uses = [ UserMapper::class ],
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface FinancialTransactionMapper {
    @Mapping(source = "payerId", target = "payer")
    @Mapping(source = "receiverId", target = "receiver")
    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    @Mapping(target = "status", constant = "ONGOING")
    fun toBean(requestDTO: FinancialTransactionReq): FinancialTransaction

    companion object {
        val INSTANCE: FinancialTransactionMapper = Mappers.getMapper(FinancialTransactionMapper::class.java)
    }
}