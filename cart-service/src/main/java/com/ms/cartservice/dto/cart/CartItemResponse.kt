package com.ms.cartservice.dto.cart

import java.time.LocalDateTime

open class CartItemResponse @JvmOverloads constructor(
    open var id: Long? = null,
    open var userId: Long? = null,
    open var productId: Long? = null,
    open var productName: String? = null,
    open var productPrice: Double? = null,
    open var quantity: Int? = null,
    open var totalPrice: Double? = null,
    open var createdAt: LocalDateTime? = null
)