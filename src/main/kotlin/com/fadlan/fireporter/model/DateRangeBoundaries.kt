package com.fadlan.fireporter.model

import java.time.LocalDate

data class DateRangeBoundaries(
    val startDate:LocalDate,
    val endDate: LocalDate,
    val period: String?=null, // Can be: All months with title case, Q1, Q2, Q3, H1, H2, All Year
    val year: Int?=null
)