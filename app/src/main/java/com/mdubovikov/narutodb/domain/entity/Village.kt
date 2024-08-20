package com.mdubovikov.narutodb.domain.entity

data class Village(
    val id: Int,
    val name: String,
    val characters: List<Character>
)