package com.mdubovikov.narutodb.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val name: String,
    val image: String
)