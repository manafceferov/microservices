package com.ms.orderservice.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
open class Order @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @Column(name = "user_id", nullable = false)
    open var userId: Long? = null,

    @Column(name = "product_id", nullable = false)
    open var productId: Long? = null,

    @Column(name = "quantity", nullable = false)
    open var quantity: Int? = null,

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    open var totalPrice: BigDecimal? = null,

    @Column(name = "status", nullable = false, length = 20)
    open var status: String? = "PENDING",

    @Column(name = "created_at", nullable = false, updatable = false)
    open var createdAt: LocalDateTime? = LocalDateTime.now()
)