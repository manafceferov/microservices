package com.ms.userservice.dto.login

import jakarta.validation.constraints.NotBlank

open class LoginRequest @JvmOverloads constructor(
    @field:NotBlank
    open var username: String? = null,

    @field:NotBlank
    open var password: String? = null
)