package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.BookmarksRepository
import javax.inject.Inject

class GetBookmarksUseCase @Inject constructor(
    private val repository: BookmarksRepository
) {

    operator fun invoke() = repository.bookmarks
}