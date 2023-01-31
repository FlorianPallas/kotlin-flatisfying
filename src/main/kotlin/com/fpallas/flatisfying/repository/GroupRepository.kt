package com.fpallas.flatisfying.repository

import com.fpallas.flatisfying.model.Group
import org.springframework.data.repository.CrudRepository

interface GroupRepository : CrudRepository<Group, Long> {
    fun findByToken(token: String): Group?
}
