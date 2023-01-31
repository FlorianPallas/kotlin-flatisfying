package com.fpallas.flatisfying.controller

import com.fpallas.flatisfying.model.*
import com.fpallas.flatisfying.model.create.CreateGroupData
import com.fpallas.flatisfying.model.update.UpdateGroupData
import com.fpallas.flatisfying.repository.GroupRepository
import com.fpallas.flatisfying.repository.PurchaseRepository
import com.fpallas.flatisfying.service.GroupService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/groups")
@Tag(name = "Groups", description = "The Group Endpoint")
class GroupController(
    private val groupService: GroupService,
    private val groupRepository: GroupRepository,
    private val purchaseRepository: PurchaseRepository
) {

    @GetMapping("/token/{token}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun joinGroup(@PathVariable token: String): Group? {
        return groupRepository.findByToken(token)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun getGroups(): List<Group> {
        return groupService.getAll()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun getGroup(@PathVariable id: Long): Group {
        return groupService.get(id) ?: throw NoSuchElementException()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun createGroup(@RequestBody data: CreateGroupData): Group {
        return groupService.create(data)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun updateGroup(@PathVariable id: Long, @RequestBody data: UpdateGroupData): Group {
        return groupService.update(id, data)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun deleteGroup(@PathVariable id: Long) {
        groupService.delete(id)
    }

    @GetMapping("/{id}/purchases")
    fun getGroupPurchases(@PathVariable id: Long): List<Purchase> {
        val group = groupService.get(id) ?: throw NoSuchElementException()
        return purchaseRepository.findAllByGroup(group)
    }

    @ExceptionHandler(NoSuchElementException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound() {
    }

}