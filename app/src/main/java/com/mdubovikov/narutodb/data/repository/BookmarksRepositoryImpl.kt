package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.data.local.db.BookmarksDao
import com.mdubovikov.narutodb.data.mapper.toDbModel
import com.mdubovikov.narutodb.data.mapper.toEntity
import com.mdubovikov.narutodb.domain.entity.ItemOfCategory
import com.mdubovikov.narutodb.domain.repository.BookmarksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarksRepositoryImpl @Inject constructor(
    private val bookmarksDao: BookmarksDao
) : BookmarksRepository {

    override val bookmarks: Flow<List<ItemOfCategory>>
        get() = bookmarksDao.getBookmarks().map { it.toEntity() }

    override fun observeIsBookmark(itemId: Int): Flow<Boolean> {
        return bookmarksDao.observeIsBookmark(itemId)
    }

    override suspend fun addToBookmarks(itemOfCategory: ItemOfCategory) {
        bookmarksDao.addToBookmarks(itemOfCategory.toDbModel())
    }

    override suspend fun removeFromBookmarks(itemId: Int) {
        bookmarksDao.removeFromBookmarks(itemId)
    }

}