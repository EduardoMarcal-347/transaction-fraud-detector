package com.detector.api.dto.mapper

import com.detector.api.dto.request.ThresholdReq
import com.detector.api.entities.Threshold
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper()
interface ThresholdMapper {
    fun toBean(requestDTO: ThresholdReq): Threshold

    companion object {
        val INSTANCE: ThresholdMapper = Mappers.getMapper(ThresholdMapper::class.java)
    }
}