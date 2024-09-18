package com.mdubovikov.narutodb.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class ItemOfCategory(
    val id: Int,
    val name: String,
    val image: String,
    val isBookmarked: Boolean
)