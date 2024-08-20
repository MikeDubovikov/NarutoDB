package com.mdubovikov.narutodb.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class KekkeigenkaiResponse(
    val kekkeigenkai: List<KekkeigenkaiDto>,
    val currentPage: Int,
    val pageSize: Int,
    val totalKekkeiGenkai: Int
)