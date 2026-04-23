package com.ms.userservice.dto

open class LoginResponse @JvmOverloads constructor(
    open var token: String? = null,
    open var username: String? = null
)