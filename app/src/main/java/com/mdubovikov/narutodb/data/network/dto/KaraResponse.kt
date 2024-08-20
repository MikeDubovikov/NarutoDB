package com.mdubovikov.narutodb.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class KaraResponse(
    val kara: List<CharacterDto>,
    val currentPage: Int,
    val pageSize: Int,
    val totalKara: Int
)