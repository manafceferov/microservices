package com.ms.orderservice.dto

import jakarta.validation.constraints.*

open class OrderUpdateRequest @JvmOverloads constructor(

    @field:NotNull
    open var id: Long? = null,

    open var status: String? = null,

    open var quantity: Int? = null
)