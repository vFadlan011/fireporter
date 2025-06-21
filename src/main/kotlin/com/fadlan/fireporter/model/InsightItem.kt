package com.fadlan.fireporter.model

import java.math.BigDecimal

data class InsightItem(
    val id: String,
    val name: String,
    val difference: BigDecimal,
    val currencyId: String,
    val currencyCode: String
)
