package com.fadlan.fireporter.dto

import kotlinx.serialization.Serializable

@Serializable
data class OpenApiLinkDto (
    val self: String? = null,
    val first: String? =null,
    val last: String? = null,
    val next: String? = null,
    val prev: String? = null,
)