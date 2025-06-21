package com.fadlan.fireporter.model

import java.math.BigDecimal

data class  ReportData(
    val dateRange: DateRangeBoundaries,
    val theme: Theme,
    val currencyCode: String,
    val currencySymbol: String,
    val currencyDecimalPlaces: Int,
    val accounts: MutableList<Account>,
    val chart: LinkedHashMap<String, BigDecimal>,
    val generalOverview: GeneralOverview,
    val incomeInsight: MutableList<InsightGroup>,
    val expenseInsight: MutableList<InsightGroup>,
    val transactionJournals: MutableList<TransactionJournal>,
    val downloadedAttachments: MutableList<Attachment>
)