package com.mdubovikov.narutodb.data.mapper

import com.mdubovikov.narutodb.data.network.dto.KekkeigenkaiDto
import com.mdubovikov.narutodb.domain.entity.ItemOfCategory
import com.mdubovikov.narutodb.domain.entity.Kekkeigenkai

fun KekkeigenkaiDto.toEntity(): Kekkeigenkai = Kekkeigenkai(
    id = id,
    name = name,
    characters = characters.toEntity()
)

fun KekkeigenkaiDto.toItemOfCategory(): ItemOfCategory = ItemOfCategory(
    id = id,
    name = name,
    image = "",
    isBookmarked = false
)

fun List<KekkeigenkaiDto>.toEntity(): List<Kekkeigenkai> = map { it.toEntity() }

fun List<KekkeigenkaiDto>.toEntityList(): List<ItemOfCategory> = map { it.toItemOfCategory() }
