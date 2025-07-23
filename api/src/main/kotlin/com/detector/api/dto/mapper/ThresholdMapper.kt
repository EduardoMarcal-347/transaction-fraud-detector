package com.detector.api.dto.mapper

import com.detector.api.dto.request.ThresholdReq
import com.detector.api.entities.Threshold
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.factory.Mappers

@Mapper()
interface ThresholdMapper {

    @Mapping(target = "id", ignore = true)
    fun toBean(requestDTO: ThresholdReq): Threshold

    companion object {
        val INSTANCE: ThresholdMapper = Mappers.getMapper(ThresholdMapper::class.java)
    }
}