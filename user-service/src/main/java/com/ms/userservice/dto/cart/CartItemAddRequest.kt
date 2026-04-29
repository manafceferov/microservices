package com.ms.userservice.dto.cart

open class CartItemAddRequest @JvmOverloads constructor(
    open var userId: Long? = null,
    open var productId: Long? = null,
    open var quantity: Int? = null
)