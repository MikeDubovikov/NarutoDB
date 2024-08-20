package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.TeamRepository
import javax.inject.Inject

class GetTeamByNameUseCase @Inject constructor(
    private val repository: TeamRepository
) {

    suspend operator fun invoke(name: String) = repository.getTeamByName(name)

}