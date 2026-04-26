package com.ms.userservice.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
open class User @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @Column(name = "username", nullable = false, unique = true, length = 50)
    open var username: String? = null,

    @Column(name = "email", nullable = false, unique = true, length = 100)
    open var email: String? = null,

    @Column(name = "password", nullable = false, length = 255)
    open var password: String? = null,

    @Column(name = "role", nullable = false, length = 20)
    open var role: String? = "ROLE_USER",

    @Column(name = "created_at", nullable = false, updatable = false)
    open var createdAt: LocalDateTime? = LocalDateTime.now()
)
