package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.ItemCard
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {

    val bookmarkedItems: Flow<List<ItemCard>>

    fun observeIsBookmarks(itemId: Int): Boolean

    suspend fun addToBookmarks(item: ItemCard)

    suspend fun removeFromBookmarks(itemId: Int)

}