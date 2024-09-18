package com.mdubovikov.narutodb.data.mapper

import com.mdubovikov.narutodb.data.local.model.BookmarkModel
import com.mdubovikov.narutodb.domain.entity.ItemOfCategory

fun ItemOfCategory.toDbModel(): BookmarkModel = BookmarkModel(
    id = id,
    name = name,
    image = image,
    isBookmarked = isBookmarked
)

fun BookmarkModel.toEntity(): ItemOfCategory = ItemOfCategory(
    id = id,
    name = name,
    image = image,
    isBookmarked = isBookmarked
)

fun List<BookmarkModel>.toEntity(): List<ItemOfCategory> = map { it.toEntity() }