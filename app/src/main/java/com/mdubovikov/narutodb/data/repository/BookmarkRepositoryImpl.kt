package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.data.local.db.BookmarksDao
import com.mdubovikov.narutodb.data.mapper.toDbModel
import com.mdubovikov.narutodb.data.mapper.toEntity
import com.mdubovikov.narutodb.domain.entity.ItemCard
import com.mdubovikov.narutodb.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarksDao: BookmarksDao
) : BookmarkRepository {

    override val bookmarkedItems: Flow<List<ItemCard>>
        get() = bookmarksDao.getBookmarks().map { it.toEntity() }

    override fun observeIsBookmarks(itemId: Int): Flow<Boolean> {
        return bookmarksDao.observeIsBookmark(itemId)
    }

    override suspend fun addToBookmarks(item: ItemCard) {
        bookmarksDao.addToBookmark(item.toDbModel())
    }

    override suspend fun removeFromBookmarks(itemId: Int) {
        bookmarksDao.removeFromBookmark(itemId)
    }

}