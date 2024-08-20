package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.TeamRepository
import javax.inject.Inject

class GetAllTeamsUseCase @Inject constructor(
    private val repository: TeamRepository
) {

    suspend operator fun invoke() = repository.getAllTeams(page = 1, limit = 10)

}