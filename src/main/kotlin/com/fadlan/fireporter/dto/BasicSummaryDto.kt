package com.fadlan.fireporter.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasicSummaryResponse(
    @SerialName("balance-in-IDR") val balanceInIDR: BasicSummaryDto,
    @SerialName("spent-in-IDR") val spentInIDR: BasicSummaryDto,
    @SerialName("earned-in-IDR") val earnedInIDR: BasicSummaryDto,
    @SerialName("bills-paid-in-IDR") val billsPaidInIDR: BasicSummaryDto,
    @SerialName("bills-unpaid-in-IDR") val billsUnpaidInIDR: BasicSummaryDto,
    @SerialName("left-to-spend-in-IDR") val leftToSpendInIDR: BasicSummaryDto,
    @SerialName("net-worth-in-IDR") val netWorthInIDR: BasicSummaryDto
)

@Serializable
data class BasicSummaryDto(
    @SerialName("key") val key: String,
    @SerialName("title") val title: String,
    @SerialName("monetary_value") val monetaryValue: String,
    @SerialName("currency_id") val currencyId: String,
    @SerialName("currency_code") val currencyCode: String,
    @SerialName("currency_symbol") val currencySymbol: String,
    @SerialName("currency_decimal_places") val currencyDecimalPlaces: Int,
    @SerialName("value_parsed") val valueParsed: String,
    @SerialName("local_icon") val localIcon: String,
    @SerialName("sub_title") val subTitle: String = "",
    @SerialName("no_available_budgets") val noAvailableBudgets: Boolean = false
)
