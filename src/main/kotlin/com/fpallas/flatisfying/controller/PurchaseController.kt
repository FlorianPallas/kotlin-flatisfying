package com.fpallas.flatisfying.controller

import com.fpallas.flatisfying.model.Purchase
import com.fpallas.flatisfying.model.create.CreatePurchaseData
import com.fpallas.flatisfying.model.update.UpdatePurchaseData
import com.fpallas.flatisfying.service.GroupService
import com.fpallas.flatisfying.service.PurchaseService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchases", description = "The Purchase Endpoint")
class PurchaseController(
    private val purchaseService: PurchaseService,
    private val groupService: GroupService,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun createPurchase(@RequestBody data: CreatePurchaseData): Purchase {
        val group = groupService.get(1) ?: throw NoSuchElementException()
        return purchaseService.create(group, data)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun getPurchases(): List<Purchase> {
        val group = groupService.get(1) ?: throw NoSuchElementException()
        return purchaseService.getAll(group)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun getPurchase(@PathVariable id: Long): Purchase {
        return purchaseService.get(id) ?: throw NoSuchElementException()
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun updatePurchase(@PathVariable id: Long, @RequestBody data: UpdatePurchaseData): Purchase {
        return purchaseService.update(id, data)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun deletePurchase(@PathVariable id: Long) {
        purchaseService.delete(id)
    }

}
