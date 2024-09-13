package com.mdubovikov.narutodb.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkModel(
    @PrimaryKey val id: Int,
    val name: String,
    val image: String
)