package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.Character
import kotlinx.coroutines.flow.Flow

interface BookmarksRepository {

    val bookmarks: Flow<List<Character>>

    fun observeIsBookmark(itemId: Int): Flow<Boolean>

    suspend fun addToBookmarks(character: Character)

    suspend fun removeFromBookmarks(itemId: Int)

}