package com.fadlan.fireporter.model

import java.math.BigDecimal

data class Account(
    val id: String,
    val name: String,
    val type: String,
    val currencyCode: String,
    val currencySymbol: String,
    val currencyDecimalPlaces: Int,
    val currentBalance: BigDecimal?= BigDecimal(0),
    val currentBalanceDate: String? = null,
    val accountNumber: String? = null,
    val iban: String? = null,
    val openingBalance: BigDecimal?= BigDecimal(0),
    val openingBalanceDate: String? = null
)