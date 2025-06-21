package com.fadlan.fireporter.repository

import com.fadlan.fireporter.dto.ChartDto
import com.fadlan.fireporter.model.Account
import com.fadlan.fireporter.model.DateRangeBoundaries
import com.fadlan.fireporter.network.CredentialProvider
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.merge
import java.math.BigDecimal
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.LinkedHashMap

class ChartRepository(
    private val ktor: HttpClient,
    private val cred: CredentialProvider
) {
    private suspend fun fetchCharts(dateRange: DateRangeBoundaries): MutableList<ChartDto> {
        val response: HttpResponse = ktor.request(cred.host) {
            url {
                appendPathSegments("api", "v1", "chart", "account", "overview")
                parameters.append("start", dateRange.startDate.toString())
                parameters.append("end", dateRange.endDate.toString())
            }

            headers.append(HttpHeaders.Authorization, "Bearer ${cred.token}")
            method = HttpMethod.Get
        }
        val chart: MutableList<ChartDto> = response.body()
        return chart
    }

    suspend fun getMergedChart(dateRange: DateRangeBoundaries): LinkedHashMap<String, BigDecimal> {
        val fetchedCharts = fetchCharts(dateRange)
        val mergedChart = LinkedHashMap<String, BigDecimal>()

        for (chart in fetchedCharts) {
            val entries = chart.entries
            for (chartEntry in entries) {
                mergedChart[chartEntry.key] = mergedChart.getOrDefault(chartEntry.key, BigDecimal.ZERO).add(chartEntry.value.toBigDecimal())
            }
        }

        return mergedChart
    }

}