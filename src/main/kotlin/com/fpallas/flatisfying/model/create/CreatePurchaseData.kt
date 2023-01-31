package com.fpallas.flatisfying.model.create

data class CreatePurchaseData(
    val name: String,
    val price: Double,
    val shares: List<CreateShareData>?,
)
