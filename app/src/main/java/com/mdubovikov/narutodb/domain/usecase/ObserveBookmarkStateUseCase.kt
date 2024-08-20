package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.BookmarkRepository
import javax.inject.Inject

class ObserveBookmarkStateUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {

    operator fun invoke(itemId: Int) = repository.observeIsBookmarks(itemId)
}