package com.mdubovikov.narutodb.data.mapper

import com.mdubovikov.narutodb.data.network.dto.ClanDto
import com.mdubovikov.narutodb.domain.entity.Clan
import com.mdubovikov.narutodb.domain.entity.ItemOfCategory

fun ClanDto.toEntity(): Clan = Clan(
    id = id,
    name = name,
    characters = characters.toEntity()
)

fun ClanDto.toItemOfCategory(): ItemOfCategory = ItemOfCategory(
    id = id,
    name = name,
    image = "",
    isBookmarked = false
)

fun List<ClanDto>.toEntity(): List<Clan> = map { it.toEntity() }

fun List<ClanDto>.toEntityList(): List<ItemOfCategory> = map { it.toItemOfCategory() }
