package com.fadlan.fireporter.repository

import com.fadlan.fireporter.dto.AccountDto
import com.fadlan.fireporter.dto.BasicSummaryResponse
import com.fadlan.fireporter.model.DateRangeBoundaries
import com.fadlan.fireporter.model.GeneralOverview
import com.fadlan.fireporter.network.CredentialProvider
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SummaryRepository(
    private val ktor: HttpClient,
    private val cred: CredentialProvider,
    private val accountRepository: AccountRepository,
){
    // get balance at date time:23.59.59
    private suspend fun getBalanceAtEndOfDate(date: LocalDate): BigDecimal {
        val accounts: Array<AccountDto> = accountRepository.fetchAccounts(date)
        var totalBalance = BigDecimal(0)

        for (account in accounts) {
            totalBalance += account.attributes.currentBalance.toBigDecimal()
        }
        return totalBalance
    }

    // get balance at date time:00.00.00
    private suspend fun getBalanceAtStartOfDate(date: LocalDate): BigDecimal {
        val accounts = accountRepository.fetchAccounts(date)
        var totalBalance = BigDecimal(0)
        for (account in accounts) {
            totalBalance += account.attributes.currentBalance.toBigDecimal()
        }

        val cashFlowYesterday = getCashFlowAtPeriod(DateRangeBoundaries(date.minusDays(1), date))
        val cashFlow = getCashFlowAtPeriod(DateRangeBoundaries(date, date.plusDays(1)))
        val expense = cashFlowYesterday.spentInIDR.monetaryValue.toBigDecimal()
        val income = cashFlow.earnedInIDR.monetaryValue.toBigDecimal()
        return totalBalance + income - expense
    }

    // return api response from endpoint /summary/basic
    private suspend fun getCashFlowAtPeriod(dateRange: DateRangeBoundaries): BasicSummaryResponse {
        val response: HttpResponse = ktor.request(cred.host) {
            url {
                appendPathSegments("api", "v1", "summary", "basic")
                parameters.append("start", dateRange.startDate.toString())
                parameters.append("end", dateRange.endDate.toString())
            }

            headers.append(HttpHeaders.Authorization, "Bearer ${cred.token}")
            method = HttpMethod.Get
        }
        val summaryResponse: BasicSummaryResponse = response.body()
        return summaryResponse
    }

    suspend fun getOverview(dateRange: DateRangeBoundaries): GeneralOverview {
        val periodicSummary = getCashFlowAtPeriod(dateRange)

        return GeneralOverview(
            getBalanceAtStartOfDate(dateRange.startDate),
            getBalanceAtEndOfDate(dateRange.endDate),
            periodicSummary.earnedInIDR.monetaryValue.toBigDecimal(),
            periodicSummary.spentInIDR.monetaryValue.toBigDecimal(),
            periodicSummary.balanceInIDR.monetaryValue.toBigDecimal(),
            periodicSummary.balanceInIDR.currencyId,
            periodicSummary.balanceInIDR.currencyCode,
            periodicSummary.balanceInIDR.currencySymbol,
        )
    }
}