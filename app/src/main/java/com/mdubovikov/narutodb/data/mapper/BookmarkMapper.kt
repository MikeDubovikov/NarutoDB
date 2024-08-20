package com.mdubovikov.narutodb.data.mapper

import com.mdubovikov.narutodb.data.local.model.BookmarkModel
import com.mdubovikov.narutodb.domain.entity.ItemCard

fun ItemCard.toDbModel(): BookmarkModel = BookmarkModel(id, name, image)

fun BookmarkModel.toEntity(): ItemCard = ItemCard(id, name, image)

fun List<BookmarkModel>.toEntity(): List<ItemCard> = map { it.toEntity() }