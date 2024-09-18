package com.mdubovikov.narutodb.data.mapper

import com.mdubovikov.narutodb.data.network.dto.TeamDto
import com.mdubovikov.narutodb.domain.entity.ItemOfCategory
import com.mdubovikov.narutodb.domain.entity.Team

fun TeamDto.toEntity(): Team = Team(
    id = id,
    name = name,
    characters = characters.toEntity()
)

fun TeamDto.toItemOfCategory(): ItemOfCategory = ItemOfCategory(
    id = id,
    name = name,
    image = "",
    isBookmarked = false
)

fun List<TeamDto>.toEntity(): List<Team> = map { it.toEntity() }

fun List<TeamDto>.toEntityList(): List<ItemOfCategory> = map { it.toItemOfCategory() }
