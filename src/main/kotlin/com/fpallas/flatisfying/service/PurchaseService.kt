package com.fpallas.flatisfying.service

import com.fpallas.flatisfying.model.Group
import com.fpallas.flatisfying.model.Purchase
import com.fpallas.flatisfying.model.create.CreatePurchaseData
import com.fpallas.flatisfying.model.update.UpdatePurchaseData
import com.fpallas.flatisfying.repository.PurchaseRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class PurchaseService(private val purchaseRepository: PurchaseRepository) {

    fun create(group: Group, data: CreatePurchaseData): Purchase {
        return purchaseRepository.save(Purchase(data.name, data.price, group))
    }

    fun getAll(group: Group): List<Purchase> {
        return purchaseRepository.findAllByGroup(group)
    }

    fun get(id: Long): Purchase? {
        return purchaseRepository.findById(id).getOrNull()
    }

    fun update(id: Long, data: UpdatePurchaseData): Purchase {
        val purchase = purchaseRepository.findById(id).orElseThrow()

        if (data.name != null) {
            purchase.name = data.name
        }
        if (data.price != null) {
            purchase.price = data.price
        }

        return purchaseRepository.save(purchase)
    }

    fun delete(id: Long) {
        purchaseRepository.deleteById(id)
    }

}