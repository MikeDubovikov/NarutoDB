package com.mdubovikov.narutodb.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse(
    val teams: List<TeamDto>,
    val currentPage: Int,
    val pageSize: Int,
    val totalTeams: Int
)