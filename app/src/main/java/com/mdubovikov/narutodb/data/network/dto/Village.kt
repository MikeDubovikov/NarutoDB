package com.mdubovikov.narutodb.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class VillageDto(
    val id: Int,
    val name: String,
    val characters: List<CharacterDto>
)