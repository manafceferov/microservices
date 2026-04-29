package com.ms.cartservice.dto.cart

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

open class CartItemAddRequest @JvmOverloads constructor(

    @field:NotNull
    open var userId: Long? = null,

    @field:NotNull
    open var productId: Long? = null,

    @field:NotNull
    @field:Min(1)
    open var quantity: Int? = null
)