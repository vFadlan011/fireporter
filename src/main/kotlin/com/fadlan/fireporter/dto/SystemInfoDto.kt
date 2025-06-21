package com.fadlan.fireporter.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SystemInfoResponse(
    val data: SystemInfoDto
)

@Serializable
data class SystemInfoDto(
    val version: String,
    @SerialName("api_version") val apiVersion: String,
    @SerialName("php_version") val phpVersion: String,
    val os: String,
    val driver: String
)