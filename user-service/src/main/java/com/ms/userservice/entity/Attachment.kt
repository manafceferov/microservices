package com.ms.userservice.entity

import jakarta.persistence.*

@Entity
@Table(name = "attachments")
open class Attachment @JvmOverloads constructor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,

    @Column(name = "file_name")
    open var fileName: String? = null,

    @Column(name = "file_url")
    open var fileUrl: String? = null,

    @Column(name = "owner_id")
    open var ownerId: Long? = null,

    @Column(name = "owner_type")
    open var ownerType: String? = null
)