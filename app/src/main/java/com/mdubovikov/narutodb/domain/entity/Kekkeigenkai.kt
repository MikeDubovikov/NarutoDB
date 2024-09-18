package com.mdubovikov.narutodb.domain.entity

data class Kekkeigenkai(
    val id: Int,
    val name: String?,
    val characters: List<Character>
)