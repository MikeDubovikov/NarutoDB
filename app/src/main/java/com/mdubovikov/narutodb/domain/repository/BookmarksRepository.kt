package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.ItemOfCategory
import kotlinx.coroutines.flow.Flow

interface BookmarksRepository {

    val bookmarks: Flow<List<ItemOfCategory>>

    fun observeIsBookmark(itemId: Int): Flow<Boolean>

    suspend fun addToBookmarks(itemOfCategory: ItemOfCategory)

    suspend fun removeFromBookmarks(itemId: Int)

}