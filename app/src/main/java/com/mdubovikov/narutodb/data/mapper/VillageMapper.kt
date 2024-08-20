package com.mdubovikov.narutodb.data.mapper

import com.mdubovikov.narutodb.data.network.dto.VillageDto
import com.mdubovikov.narutodb.domain.entity.Village

fun VillageDto.toEntity(): Village = Village(
    id = id,
    name = name,
    characters = characters.toEntity()
)

fun List<VillageDto>.toEntity(): List<Village> = map { it.toEntity() }