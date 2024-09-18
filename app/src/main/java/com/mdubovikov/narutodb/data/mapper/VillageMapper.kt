package com.mdubovikov.narutodb.data.mapper

import com.mdubovikov.narutodb.data.network.dto.VillageDto
import com.mdubovikov.narutodb.domain.entity.ItemOfCategory
import com.mdubovikov.narutodb.domain.entity.Village

fun VillageDto.toEntity(): Village = Village(
    id = id,
    name = name,
    characters = characters.toEntity()
)

fun VillageDto.toItemOfCategory(): ItemOfCategory = ItemOfCategory(
    id = id,
    name = name,
    image = "",
    isBookmarked = false
)

fun List<VillageDto>.toEntity(): List<Village> = map { it.toEntity() }

fun List<VillageDto>.toEntityList(): List<ItemOfCategory> = map { it.toItemOfCategory() }
