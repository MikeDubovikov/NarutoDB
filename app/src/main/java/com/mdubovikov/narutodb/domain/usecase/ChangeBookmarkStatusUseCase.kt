package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.entity.ItemCard
import com.mdubovikov.narutodb.domain.repository.BookmarkRepository
import javax.inject.Inject

class ChangeBookmarkStatusUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {

    suspend fun addToBookmarks(item: ItemCard) = repository.addToBookmarks(item)

    suspend fun removeFromBookmarks(itemId: Int) = repository.removeFromBookmarks(itemId)

}