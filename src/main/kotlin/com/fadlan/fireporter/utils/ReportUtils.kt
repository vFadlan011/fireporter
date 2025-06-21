package com.fadlan.fireporter.utils

import com.fadlan.fireporter.model.DateRangeBoundaries
import java.math.BigDecimal
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReportUtils {
    companion object {
        @JvmStatic
        fun formatCurrency(
            currencySymbol: String = "",
            amount: BigDecimal = BigDecimal.ZERO,
            decimalPlaces: Int = 2
        ): String {
            val patternBuilder = StringBuilder("#,##0")
            if (decimalPlaces > 0) {
                patternBuilder.append(".")
                for (i in 0 until decimalPlaces) {
                    patternBuilder.append("0")
                }
            }

            val formatter = DecimalFormat(patternBuilder.toString())
            return currencySymbol + " " + formatter.format(amount)
        }

        @JvmStatic
        fun formatDate(date: LocalDate?): String {
            if (date == null) return ""

            return date.format(DateTimeFormatter.ofPattern("d MMMM yyyy"))
        }

        @JvmStatic
        fun getPeriod(dateRange: DateRangeBoundaries): String {
            return when (dateRange.period) {
                "Q1", "Q2", "Q3", "H1", "H2" -> "${dateRange.period} ${dateRange.year} (${formatDate(dateRange.startDate)}—${formatDate(dateRange.endDate)})"
                else -> "${dateRange.period} ${dateRange.year}"
            }
        }
    }
}