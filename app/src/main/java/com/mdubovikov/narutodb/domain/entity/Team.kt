package com.mdubovikov.narutodb.domain.entity

data class Team(
    val id: Int,
    val name: String,
    val characters: List<Character>
)