package com.fadlan.fireporter.model

data class InsightGroup(
    val type: InsightType,
    val filter: String,
    val insights: List<InsightItem>
)

enum class InsightType {
    INCOME,
    EXPENSE
}