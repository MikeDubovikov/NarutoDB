package com.mdubovikov.narutodb.data.mapper

import com.mdubovikov.narutodb.data.local.model.BookmarkModel
import com.mdubovikov.narutodb.domain.entity.Character

fun Character.toCharacterDbModel(): BookmarkModel = BookmarkModel(
    id = id,
    name = name,
    image = image,
    isBookmarked = isBookmarked
)

fun BookmarkModel.toCharacter(): Character =
    Character(
        id = id,
        name = name,
        image = image,
        isBookmarked = isBookmarked
    )

fun List<BookmarkModel>.toEntityBookmarks(): List<Character> = map { it.toCharacter() }