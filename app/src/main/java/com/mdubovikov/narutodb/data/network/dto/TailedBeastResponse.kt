package com.mdubovikov.narutodb.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class TailedBeastResponse(
    val tailedBeasts: List<CharacterDto>,
    val currentPage: Int,
    val pageSize: Int,
    val totalTailedBeasts: Int
)