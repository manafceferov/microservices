package com.ms.productservice.dto

import jakarta.validation.constraints.*
import java.math.BigDecimal

open class ProductCreateRequest @JvmOverloads constructor(

    @field:NotBlank
    @field:Size(max = 100)
    open var name: String? = null,

    open var description: String? = null,

    @field:NotNull
    @field:DecimalMin("0.0")
    open var price: BigDecimal? = null,

    @field:Min(0)
    open var stock: Int? = 0
)