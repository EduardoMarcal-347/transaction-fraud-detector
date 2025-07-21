package com.detector.api.dto.mapper

import com.detector.api.dto.request.UserReq
import com.detector.api.entities.User
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import java.util.UUID

@Mapper(uses = [BankAccountMapper::class])
interface UserMapper {
    fun toBean(requestDTO: UserReq): User

    fun fromId(id: UUID): User {
        return User(id = id)
    }

    companion object {
        val INSTANCE: UserMapper = Mappers.getMapper(UserMapper::class.java)
    }
}