package com.fpallas.flatisfying.controller

import com.fpallas.flatisfying.model.*
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/groups")
@Tag(name = "Groups", description = "The Group Endpoint")
class ShareController {

    @ExceptionHandler(NoSuchElementException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound() {
    }

}