package com.ms.cartservice.dto.cart

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

open class CartItemUpdateRequest @JvmOverloads constructor(

    @field:NotNull
    open var id: Long? = null,

    @field:NotNull
    @field:Min(1)
    open var quantity: Int? = null
)