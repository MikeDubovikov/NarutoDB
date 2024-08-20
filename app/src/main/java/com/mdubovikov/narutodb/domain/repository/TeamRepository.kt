package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.Team

interface TeamRepository {

    suspend fun getAllTeams(page: Int, limit: Int): List<Team>

    suspend fun getTeamById(teamId: Int): Team

    suspend fun getTeamByName(name: String): Team

}