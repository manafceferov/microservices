package com.ms.userservice.dto

import jakarta.validation.constraints.*

open class UserCreateRequest @JvmOverloads constructor(

    @field:NotBlank
    @field:Size(min = 3, max = 50)
    open var username: String? = null,

    @field:NotBlank
    @field:Email
    @field:Size(max = 100)
    open var email: String? = null,

    @field:NotBlank
    @field:Size(min = 6, max = 255)
    open var password: String? = null
)