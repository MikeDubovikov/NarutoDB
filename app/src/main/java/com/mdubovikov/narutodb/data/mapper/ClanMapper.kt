package com.mdubovikov.narutodb.data.mapper

import com.mdubovikov.narutodb.data.network.dto.ClanDto
import com.mdubovikov.narutodb.domain.entity.Clan

fun ClanDto.toEntity(): Clan = Clan(
    id = id,
    name = name,
    characters = characters.toEntity()
)

fun List<ClanDto>.toEntity(): List<Clan> = map { it.toEntity() }