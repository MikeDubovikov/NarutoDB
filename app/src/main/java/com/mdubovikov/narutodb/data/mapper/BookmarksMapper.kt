package com.mdubovikov.narutodb.data.mapper

import com.mdubovikov.narutodb.data.local.model.BookmarkModel
import com.mdubovikov.narutodb.domain.entity.ItemOfCategory

fun ItemOfCategory.toDbModel(): BookmarkModel = BookmarkModel(id, name, image)

fun BookmarkModel.toEntity(): ItemOfCategory = ItemOfCategory(id, name, image)

fun List<BookmarkModel>.toEntity(): List<ItemOfCategory> = map { it.toEntity() }