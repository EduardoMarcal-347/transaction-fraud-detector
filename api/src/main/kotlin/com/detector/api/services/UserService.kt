package com.detector.api.services

import com.detector.api.dto.mapper.UserMapper
import com.detector.api.dto.request.UserReq
import com.detector.api.entities.User
import com.detector.api.repositories.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService (
    @Value($$"${kafka.topics.threshold}")
    val thresholdTopic: String,
    val userRepository: UserRepository,
    val kafkaTemplate: KafkaTemplate<String, Any?>,
){

    fun createAccount(req: UserReq): User{
        val user = UserMapper.INSTANCE.toBean(req)
        // validate
        // cast to response

        val entity = userRepository.save(user)
        kafkaTemplate.send(thresholdTopic, entity.id.toString(), entity.bankAccount!!.threshold)
        return entity
    }

    fun findUser(id: UUID): User {
        return userRepository.findById(id)
            .orElseThrow { NoSuchElementException("User with id: $id doesn't exists") }
    }

    fun deleteAccount(id: UUID) {
        if (userExists(id)) {
            userRepository.deleteById(id)
        }
    }

    fun userExists(id: UUID): Boolean {
        return userRepository.existsById(id)
    }

    fun validateUserExists(id: UUID, transactionRole: String) {
        if (!userExists(id)) {
            throw NoSuchElementException("$transactionRole with id $id doesn't exists.")
        }
    }

}