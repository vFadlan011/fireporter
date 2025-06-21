package com.fadlan.fireporter.repository

import com.fadlan.fireporter.dto.AccountDto
import com.fadlan.fireporter.dto.AccountResponse
import com.fadlan.fireporter.model.Account
import com.fadlan.fireporter.model.DateRangeBoundaries
import com.fadlan.fireporter.network.CredentialProvider
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// (Asset Account)
class AccountRepository(
    private val ktor: HttpClient,
    private val cred: CredentialProvider,
) {
    private suspend fun fetchSinglePageAccounts(page: Int, date: LocalDate): AccountResponse {
        val response: HttpResponse = ktor.request(cred.host) {
            url {
                appendPathSegments("api", "v1", "accounts")
                parameters.append("type", "asset")
                parameters.append("date", date.toString())
                parameters.append("page", page.toString())
            }

            headers.append(HttpHeaders.Authorization, "Bearer ${cred.token}")
            method = HttpMethod.Get
        }
        return response.body()
    }

    suspend fun fetchAccounts(date: LocalDate): Array<AccountDto> {
        var currentPage = 1
        val accountResponse = fetchSinglePageAccounts(currentPage, date)
        val totalPages = accountResponse.meta?.pagination?.totalPages ?: 1

        var accounts: Array<AccountDto> = accountResponse.data

        while (currentPage < totalPages) {
            currentPage++
            accounts += fetchSinglePageAccounts(currentPage, date).data
        }
        return accounts
    }

    suspend fun getAccountStatistics(dateRange: DateRangeBoundaries):  MutableList<Account> {
        val fetchedAccounts = fetchAccounts(dateRange.endDate)

        val accounts = mutableListOf<Account>()
        for (account in fetchedAccounts) {
            val attr = account.attributes
            accounts.add(
                Account(
                    account.id,
                    attr.name,
                    attr.type,
                    attr.currencyCode,
                    attr.currencySymbol,
                    attr.currencyDecimalPlaces,
                    attr.currentBalance.toBigDecimal(),
                    attr.currentBalanceDate,
                    attr.accountNumber,
                    attr.iban,
                    attr.openingBalance.toBigDecimal(),
                    attr.openingBalanceDate
                )
            )
        }

        return accounts
    }

    fun hasActiveAccountInRange(dateRange: DateRangeBoundaries, accounts: MutableList<Account>): Boolean {
        val textDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
        var result = false

        for (account in accounts) {
            val openingBalanceDateStr = account.openingBalanceDate
            if (openingBalanceDateStr != null) {
                val openingDate = openingBalanceDateStr.let { LocalDate.parse(it, textDateFormat) }
                if (openingDate.isBefore(dateRange.startDate) || openingDate.isBefore(dateRange.endDate)) {
                    result = true
                    break
                }
            }
        }

        return result
    }
}