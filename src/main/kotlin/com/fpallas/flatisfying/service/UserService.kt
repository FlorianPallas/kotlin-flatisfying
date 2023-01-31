package com.fpallas.flatisfying.service

import com.fpallas.flatisfying.model.create.CreateUserData
import com.fpallas.flatisfying.model.update.UpdateUserData
import com.fpallas.flatisfying.model.User
import com.fpallas.flatisfying.repository.UserRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class UserService(private val userRepository: UserRepository) {

    fun create(data: CreateUserData): User {
        return userRepository.save(User(data))
    }

    fun getAll(): List<User> {
        return userRepository.findAll().toList()
    }

    fun get(id: Long): User? {
        return userRepository.findById(id).getOrNull()
    }

    fun update(id: Long, data: UpdateUserData): User {
        val user = userRepository.findById(id).orElseThrow()

        if (data.name != null) {
            user.name = data.name
        }

        return userRepository.save(user)
    }

    fun delete(id: Long) {
        userRepository.deleteById(id)
    }

}
