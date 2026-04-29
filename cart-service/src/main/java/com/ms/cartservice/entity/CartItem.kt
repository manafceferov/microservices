package com.ms.cartservice.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "cart_items")
open class CartItem @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @Column(name = "user_id", nullable = false)
    open var userId: Long? = null,

    @Column(name = "product_id", nullable = false)
    open var productId: Long? = null,

    @Column(name = "quantity", nullable = false)
    open var quantity: Int? = null,

    @Column(name = "created_at", nullable = false, updatable = false)
    open var createdAt: LocalDateTime? = LocalDateTime.now()
)