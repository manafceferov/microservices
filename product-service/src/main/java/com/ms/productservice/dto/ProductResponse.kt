package com.ms.productservice.dto

import java.math.BigDecimal
import java.time.LocalDateTime

open class ProductResponse @JvmOverloads constructor(
    open var id: Long? = null,
    open var name: String? = null,
    open var description: String? = null,
    open var price: BigDecimal? = null,
    open var stock: Int? = null,
    open var createdAt: LocalDateTime? = null
)