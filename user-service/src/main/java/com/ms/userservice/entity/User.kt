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

    @Column(name = "first_name", length = 100)
    open var firstName: String? = null,

    @Column(name = "last_name", length = 100)
    open var lastName: String? = null,

    @Column(name = "email", nullable = false, unique = true, length = 100)
    open var email: String? = null,

    @Column(name = "password", nullable = false, length = 255)
    open var password: String? = null,

    @Column(name = "phone_number", length = 20)
    open var phoneNumber: String? = null,

    @Column(name = "birth_date")
    open var birthDate: String? = null,

    @Column(name = "gender", length = 10)
    open var gender: String? = null,

    @Column(name = "status", nullable = false)
    open var status: Boolean = true,

    @Column(name = "role", nullable = false, length = 20)
    open var role: String? = "ROLE_USER",

    @Column(name = "created_at", nullable = false, updatable = false)
    open var createdAt: LocalDateTime? = LocalDateTime.now()
)