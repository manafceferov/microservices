package com.ms.userservice.dto.user

import java.time.LocalDateTime

open class UserResponse @JvmOverloads constructor(
    open var id: Long? = null,
    open var username: String? = null,
    open var firstName: String? = null,
    open var lastName: String? = null,
    open var email: String? = null,
    open var phoneNumber: String? = null,
    open var birthDate: String? = null,
    open var gender: String? = null,
    open var status: Boolean? = null,
    open var role: String? = null,
    open var createdAt: LocalDateTime? = null
)