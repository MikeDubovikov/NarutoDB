package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.entity.ItemOfCategory
import com.mdubovikov.narutodb.domain.repository.BookmarksRepository
import javax.inject.Inject

class ChangeBookmarkStatusUseCase @Inject constructor(
    private val repository: BookmarksRepository
) {

    suspend fun addToBookmarks(itemOfCategory: ItemOfCategory) = repository.addToBookmarks(itemOfCategory)

    suspend fun removeFromBookmarks(itemId: Int) = repository.removeFromBookmarks(itemId)

}