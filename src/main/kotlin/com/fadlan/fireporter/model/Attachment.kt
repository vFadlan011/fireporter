package com.fadlan.fireporter.model

import java.io.File
import java.time.LocalDateTime

data class Attachment(
    val type: String,
    val id: String,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,

    val attachableId: String,
    val attachableType: String,

    val md5: String,
    val hash: String,
    var filename: String,

    val downloadUrl: String,
    val uploadUrl: String,

    var title: String?=null,
    val notes: String?=null,

    val mime: String,
    val size: Int,
    var file: File?=null,
    var imageFiles: MutableList<File>,

    var elementId: String?=null,
    var parentId: String?=null
)
