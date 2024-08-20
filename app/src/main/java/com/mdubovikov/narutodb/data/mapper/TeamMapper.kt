package com.mdubovikov.narutodb.data.mapper

import com.mdubovikov.narutodb.data.network.dto.TeamDto
import com.mdubovikov.narutodb.domain.entity.Team

fun TeamDto.toEntity(): Team = Team(
    id = id,
    name = name,
    characters = characters.toEntity()
)

fun List<TeamDto>.toEntity(): List<Team> = map { it.toEntity() }