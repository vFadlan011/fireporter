package com.fadlan.fireporter.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InsightItemDto(
    val id: String,
    val name: String,
    val difference: String,
    @SerialName("difference_float") val differenceFloat: Float,
    @SerialName("currency_id") val currencyId: String,
    @SerialName("currency_code") val currencyCode: String,
)
