package com.mdubovikov.narutodb.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val characters: List<CharacterDto>,
    val currentPage: Int,
    val pageSize: Int,
    val totalCharacters: Int
)