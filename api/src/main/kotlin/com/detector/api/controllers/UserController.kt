package com.detector.api.controllers

import com.detector.api.dto.request.UserReq
import com.detector.api.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@Controller
@RequestMapping("/api/user/")
class UserController(
    val userService: UserService
) {

    @PostMapping
    fun createUserAccount(@RequestBody request : UserReq) : ResponseEntity<Any>{
        val response = userService.createAccount(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("{id}")
    fun findUserAccount(@PathVariable userId : UUID) {
        userService.findUser(userId)
    }

    @DeleteMapping("{id}")
    fun deleteUserAccount(@PathVariable userId : UUID) {
        userService.deleteAccount(userId)
    }
}