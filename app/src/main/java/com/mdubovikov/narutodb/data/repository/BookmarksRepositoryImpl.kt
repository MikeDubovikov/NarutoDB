package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.data.local.db.BookmarksDao
import com.mdubovikov.narutodb.data.mapper.toCharacterDbModel
import com.mdubovikov.narutodb.data.mapper.toEntityBookmarks
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.domain.repository.BookmarksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarksRepositoryImpl @Inject constructor(
    private val bookmarksDao: BookmarksDao
) : BookmarksRepository {

    override val bookmarks: Flow<List<Character>>
        get() = bookmarksDao.getBookmarks().map { it.toEntityBookmarks() }

    override fun observeIsBookmark(itemId: Int): Flow<Boolean> {
        return bookmarksDao.observeIsBookmark(itemId)
    }

    override suspend fun addToBookmarks(character: Character) {
        bookmarksDao.addToBookmarks(character.toCharacterDbModel())
    }

    override suspend fun removeFromBookmarks(itemId: Int) {
        bookmarksDao.removeFromBookmarks(itemId)
    }

}