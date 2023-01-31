package com.fpallas.flatisfying.model.update

data class UpdatePurchaseData(
    val name: String?,
    val price: Double?,
    val shares: Map<Long, UpdateShareData>?,
)
