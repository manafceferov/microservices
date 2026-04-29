package com.ms.userservice.cart.dto

open class CartItem @JvmOverloads constructor(
    open var productId: Long? = null,
    open var productName: String? = null,
    open var price: Double? = null,
    open var quantity: Int? = null
) {
    open val totalPrice: Double
        get() = (price ?: 0.0) * (quantity ?: 0)
}