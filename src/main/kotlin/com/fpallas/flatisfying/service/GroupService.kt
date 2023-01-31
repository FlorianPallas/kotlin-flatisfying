package com.fpallas.flatisfying.service

import com.fpallas.flatisfying.model.create.CreateGroupData
import com.fpallas.flatisfying.model.Group
import com.fpallas.flatisfying.model.update.UpdateGroupData
import com.fpallas.flatisfying.repository.GroupRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class GroupService(private val groupRepository: GroupRepository) {

    fun getAll(): List<Group> {
        return groupRepository.findAll().toList()
    }

    fun get(id: Long): Group? {
        return groupRepository.findById(id).getOrNull()
    }

    fun create(data: CreateGroupData): Group {
        val group = Group(data)
        return groupRepository.save(group)
    }

    fun update(id: Long, data: UpdateGroupData): Group {
        val group = groupRepository.findById(id).orElseThrow()
        if (data.name != null) {
            group.name = data.name
        }
        return groupRepository.save(group)
    }

    fun delete(id: Long) {
        groupRepository.deleteById(id)
    }

}