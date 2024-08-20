package com.mdubovikov.narutodb.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemCard(
    val id: Int,
    val name: String,
    val image: String
) : Parcelable