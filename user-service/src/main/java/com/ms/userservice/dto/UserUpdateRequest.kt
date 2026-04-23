package com.ms.userservice.dto

import jakarta.validation.constraints.*

open class UserUpdateRequest @JvmOverloads constructor(

    @field:NotNull
    open var id: Long? = null,

    @field:Size(min = 3, max = 50)
    open var username: String? = null,

    @field:Email
    @field:Size(max = 100)
    open var email: String? = null,

    @field:Size(min = 6, max = 255)
    open var password: String? = null
)