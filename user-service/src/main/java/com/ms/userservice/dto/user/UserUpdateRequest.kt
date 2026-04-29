package com.ms.userservice.dto.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

open class UserUpdateRequest @JvmOverloads constructor(

    @field:NotNull
    open var id: Long? = null,

    @field:Size(min = 3, max = 50)
    open var username: String? = null,

    @field:Size(max = 100)
    open var firstName: String? = null,

    @field:Size(max = 100)
    open var lastName: String? = null,

    @field:Email
    @field:Size(max = 100)
    open var email: String? = null,

    @field:Size(min = 6, max = 255)
    open var password: String? = null,

    open var phoneNumber: String? = null,
    open var birthDate: String? = null,
    open var gender: String? = null
)