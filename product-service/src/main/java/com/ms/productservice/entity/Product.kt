package com.ms.productservice.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "products")
open class Product @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @Column(name = "name", nullable = false, length = 100)
    open var name: String? = null,

    @Column(name = "description", columnDefinition = "TEXT")
    open var description: String? = null,

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    open var price: BigDecimal? = null,

    @Column(name = "stock", nullable = false)
    open var stock: Int? = 0,

    @Column(name = "created_at", nullable = false, updatable = false)
    open var createdAt: LocalDateTime? = LocalDateTime.now()
)