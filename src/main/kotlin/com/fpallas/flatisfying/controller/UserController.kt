package com.fpallas.flatisfying.controller

import com.fpallas.flatisfying.model.Share
import com.fpallas.flatisfying.model.create.CreateUserData
import com.fpallas.flatisfying.model.update.UpdateUserData
import com.fpallas.flatisfying.model.User
import com.fpallas.flatisfying.repository.ShareRepository
import com.fpallas.flatisfying.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "The User Endpoint")
class UserController(private val userService: UserService, val shareRepository: ShareRepository) {

    @GetMapping
    @Operation(summary = "Get a list of all users")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun getUsers(): List<User> {
        return userService.getAll()
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by id")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun getUser(@PathVariable id: Long): User {
        return userService.get(id) ?: throw NoSuchElementException()
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun createUser(@RequestBody data: CreateUserData): User {
        return userService.create(data)
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing user")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun updateUser(@PathVariable id: Long, @RequestBody data: UpdateUserData): User {
        return userService.update(id, data)
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun deleteUser(@PathVariable id: Long) {
        userService.delete(id)
    }

    @GetMapping("/{id}/shares")
    fun getUserShares(@PathVariable id: Long): List<Share> {
        return shareRepository.findAllByUserId(id)
    }

    @ExceptionHandler(NoSuchElementException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound() {
    }

}