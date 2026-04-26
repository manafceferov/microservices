package com.ms.orderservice.dto

import jakarta.validation.constraints.*

open class OrderCreateRequest @JvmOverloads constructor(

    @field:NotNull
    open var userId: Long? = null,

    @field:NotNull
    open var productId: Long? = null,

    @field:NotNull
    @field:Min(1)
    open var quantity: Int? = null
)