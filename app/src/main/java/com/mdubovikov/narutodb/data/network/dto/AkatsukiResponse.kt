package com.mdubovikov.narutodb.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class AkatsukiResponse(
    val akatsuki: List<CharacterDto>,
    val currentPage: Int,
    val pageSize: Int,
    val totalMembers: Int
)