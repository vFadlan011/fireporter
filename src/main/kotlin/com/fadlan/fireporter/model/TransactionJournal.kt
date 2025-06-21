package com.fadlan.fireporter.model

import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionJournal(
    val transactionJournalId: String,
    val type: String,
    val datetime: LocalDateTime,
    val order: Int,

    val currencyCode: String,
    val currencySymbol: String,

    val amount: BigDecimal,
    val description: String,

    val sourceId: String,
    val sourceName: String,
    val sourceType: String,

    val destinationId: String,
    val destinationName: String,
    val destinationType: String,

    val budgetId: String?=null,
    val budgetName: String?=null,

    val categoryId: String?=null,
    val categoryName: String?=null,

    val billId: String?=null,
    val billName: String?=null,

    val tags: List<String>,

    val hasAttachments: Boolean,
    val attachments: MutableList<Attachment>,

    val balanceLeft: BigDecimal?=null,

    var elementId: String?=null
)
