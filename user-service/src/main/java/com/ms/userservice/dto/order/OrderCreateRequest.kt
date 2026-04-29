package com.ms.userservice.dto.order

open class OrderCreateRequest @JvmOverloads constructor(
    open var userId: Long? = null,
    open var productId: Long? = null,
    open var quantity: Int? = null
)