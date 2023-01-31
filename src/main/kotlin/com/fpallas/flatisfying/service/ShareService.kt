package com.fpallas.flatisfying.service

import com.fpallas.flatisfying.model.*
import com.fpallas.flatisfying.model.create.CreateShareData
import com.fpallas.flatisfying.repository.ShareRepository
import org.springframework.stereotype.Service

@Service
class ShareService(private val shareRepository: ShareRepository) {

    fun create(group: Group, purchase: Purchase, user: User, data: CreateShareData): Share {
        return shareRepository.save(Share(data.price, group, purchase, user))
    }

}