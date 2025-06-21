package com.fadlan.fireporter.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaDto(
    val pagination: MetaPaginationDto
)

@Serializable
data class MetaPaginationDto(
    val total: Int,
    val count: Int,
    @SerialName("per_page") val perPage: Int,
    @SerialName("current_page") val currentPage: Int,
    @SerialName("total_pages") val totalPages: Int
)
