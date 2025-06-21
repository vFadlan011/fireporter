package com.fadlan.fireporter.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChartDto(
    val label: String,
    @SerialName("currency_id")  val currencyId: String,
    @SerialName("currency_code") val currencyCode: String,
    @SerialName("currency_symbol") val currencySymbol: String,
    @SerialName("currency_decimal_places") val currencyDecimalPlaces: Int,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String,
    val type: String,
    val yAxisID: Int,
    val entries: LinkedHashMap<String, String>
)