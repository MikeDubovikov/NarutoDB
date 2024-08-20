package com.mdubovikov.narutodb.data.mapper

import com.mdubovikov.narutodb.data.network.dto.KekkeigenkaiDto
import com.mdubovikov.narutodb.domain.entity.Kekkeigenkai

fun KekkeigenkaiDto.toEntity(): Kekkeigenkai = Kekkeigenkai(
    id = id,
    name = name,
    characters = characters.toEntity()
)

fun List<KekkeigenkaiDto>.toEntity(): List<Kekkeigenkai> = map { it.toEntity() }