package com.fadlan.fireporter.utils

import com.fadlan.fireporter.model.DateRangeBoundaries
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
import java.time.LocalDate

class ReportUtilsTest : StringSpec({
    // formatCurrency
    "should format currency with 2 decimal places and symbol" {
        ReportUtils.formatCurrency("$", 1234.5.toBigDecimal(), 2) shouldBe "$ 1,234.50"
    }

    "should format currency with 0 decimal places" {
        ReportUtils.formatCurrency("Rp", BigDecimal("1000000"), 0) shouldBe "Rp 1,000,000"
    }

//    "should default null amount to zero" {
//        ReportUtils.formatCurrency("€", null, 2) shouldBe "€ 0.00"
//    }
//
//    "should default null symbol to empty" {
//        ReportUtils.formatCurrency(null, 99.99.toBigDecimal(), 2) shouldBe " 99.99"
//    }
//
//    "should default null decimalPlaces to 2" {
//        ReportUtils.formatCurrency("¥", BigDecimal("42"), null) shouldBe "¥ 42.00"
//    }

    "should format with 3 decimals" {
        ReportUtils.formatCurrency("¢", 56.78.toBigDecimal(), 3) shouldBe "¢ 56.780"
    }

    "should format large number with 3 decimals" {
        ReportUtils.formatCurrency("£", BigDecimal("1234567.891"), 3) shouldBe "£ 1,234,567.891"
    }


    // formatDate
    "should format LocalDate to full English date" {
        val date = LocalDate.of(2023, 6, 18)
        ReportUtils.formatDate(date) shouldBe "18 June 2023"
    }

    "should return empty string for null date" {
        ReportUtils.formatDate(null) shouldBe ""
    }

    "should format single-digit day and month correctly" {
        val date = LocalDate.of(2021, 1, 5)
        ReportUtils.formatDate(date) shouldBe "5 January 2021"
    }

    // getPeriod
    "should return formatted string for Q1" {
        val range = DateRangeBoundaries(
            LocalDate.of(2023, 1, 1),
            LocalDate.of(2023, 3, 31),
            "Q1",
            2023
        )
        ReportUtils.getPeriod(range) shouldBe "Q1 2023 (1 January 2023—31 March 2023)"
    }

    "should return formatted string for H2" {
        val range = DateRangeBoundaries(
            LocalDate.of(2022, 7, 1),
            LocalDate.of(2022, 12, 31),
            "H2",
            2022
        )
        ReportUtils.getPeriod(range) shouldBe "H2 2022 (1 July 2022—31 December 2022)"
    }

    "should return simple string for single month period" {
        val range = DateRangeBoundaries(
            LocalDate.of(2021, 5, 1),
            LocalDate.of(2021, 5, 31),
            "May",
            2021
        )
        ReportUtils.getPeriod(range) shouldBe "May 2021"
    }

    "should return simple string for All Year" {
        val range = DateRangeBoundaries(
            LocalDate.of(2020, 1, 1),
            LocalDate.of(2020, 12, 31),
            "All Year",
            2020
        )
        ReportUtils.getPeriod(range) shouldBe "All Year 2020"
    }
})
