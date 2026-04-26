package com.ms.orderservice.dto

import java.math.BigDecimal
import java.time.LocalDateTime

open class OrderResponse @JvmOverloads constructor(
    open var id: Long? = null,
    open var userId: Long? = null,
    open var productId: Long? = null,
    open var quantity: Int? = null,
    open var totalPrice: BigDecimal? = null,
    open var status: String? = null,
    open var createdAt: LocalDateTime? = null
)