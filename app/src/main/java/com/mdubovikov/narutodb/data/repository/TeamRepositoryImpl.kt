package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.data.mapper.toEntity
import com.mdubovikov.narutodb.data.network.api.ApiService
import com.mdubovikov.narutodb.domain.entity.Team
import com.mdubovikov.narutodb.domain.repository.TeamRepository
import javax.inject.Inject

class TeamRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : TeamRepository {

    override suspend fun getAllTeams(page: Int, limit: Int): List<Team> {
        return apiService.getAllTeams(page, limit).teams.toEntity()
    }

    override suspend fun getTeamById(teamId: Int): Team {
        return apiService.getTeamById(teamId).toEntity()
    }

    override suspend fun getTeamByName(name: String): Team {
        return apiService.getTeamByName(name).toEntity()
    }

}