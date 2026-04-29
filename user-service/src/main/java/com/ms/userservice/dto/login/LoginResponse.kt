package com.ms.userservice.dto.login

open class LoginResponse @JvmOverloads constructor(
    open var token: String? = null,
    open var username: String? = null,
    open var userId: Long? = null
)