package com.mdubovikov.narutodb.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val name: String,
    val description: String,
    val image: String
)