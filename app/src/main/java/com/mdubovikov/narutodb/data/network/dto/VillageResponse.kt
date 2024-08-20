package com.mdubovikov.narutodb.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class VillageResponse(
    val villages: List<VillageDto>,
    val currentPage: Int,
    val pageSize: Int,
    val totalVillages: Int
)