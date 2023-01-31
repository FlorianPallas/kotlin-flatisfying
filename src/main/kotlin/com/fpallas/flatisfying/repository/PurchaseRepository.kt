package com.fpallas.flatisfying.repository

import com.fpallas.flatisfying.model.Group
import com.fpallas.flatisfying.model.Purchase
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository : CrudRepository<Purchase, Long> {
    fun findAllByGroup(group: Group): List<Purchase>

}
