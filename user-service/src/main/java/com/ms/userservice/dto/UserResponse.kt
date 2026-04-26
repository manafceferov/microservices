package com.ms.userservice.dto

import java.time.LocalDateTime

open class UserResponse @JvmOverloads constructor(
    open var id: Long? = null,
    open var username: String? = null,
    open var email: String? = null,
    open var role: String? = null,
    open var createdAt: LocalDateTime? = null
)