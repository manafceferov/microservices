package com.ms.productservice.dto

import jakarta.validation.constraints.*
import java.math.BigDecimal

open class ProductUpdateRequest @JvmOverloads constructor(

    @field:NotNull
    open var id: Long? = null,

    @field:Size(max = 100)
    open var name: String? = null,

    open var description: String? = null,

    @field:DecimalMin("0.0")
    open var price: BigDecimal? = null,

    @field:Min(0)
    open var stock: Int? = null
)