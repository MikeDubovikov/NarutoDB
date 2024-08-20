package com.mdubovikov.narutodb.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class ClanResponse(
    val clans: List<ClanDto>,
    val currentPage: Int,
    val pageSize: Int,
    val totalClans: Int
)