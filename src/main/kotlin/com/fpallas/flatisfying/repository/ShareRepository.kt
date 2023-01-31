package com.fpallas.flatisfying.repository

import com.fpallas.flatisfying.model.Share
import org.springframework.data.repository.CrudRepository

interface ShareRepository : CrudRepository<Share, Long> {
    fun findAllByUserId(userId: Long): List<Share>
}
