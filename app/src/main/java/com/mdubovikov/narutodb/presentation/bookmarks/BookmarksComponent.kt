package com.mdubovikov.narutodb.presentation.bookmarks

import com.mdubovikov.narutodb.domain.entity.Character
import kotlinx.coroutines.flow.StateFlow

interface BookmarksComponent {

    val model: StateFlow<BookmarksStore.State>

    fun onBookmarkClick(character: Character)

    fun onClickBack()
}