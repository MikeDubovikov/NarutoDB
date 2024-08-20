package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.TeamRepository
import javax.inject.Inject

class GetTeamByIdUseCase @Inject constructor(
    private val repository: TeamRepository
) {

    suspend operator fun invoke(teamId: Int) = repository.getTeamById(teamId)

}