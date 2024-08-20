package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.BookmarkRepository
import javax.inject.Inject

class GetBookmarkedItemUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    operator fun invoke() = repository.bookmarkedItems
}