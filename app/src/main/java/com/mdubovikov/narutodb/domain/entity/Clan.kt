package com.mdubovikov.narutodb.domain.entity

data class Clan(
    val id: Int,
    val name: String,
    val characters: List<Character>
)