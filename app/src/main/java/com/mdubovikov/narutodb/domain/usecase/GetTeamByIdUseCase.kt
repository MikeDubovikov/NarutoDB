package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.SearchRepository
import javax.inject.Inject

class GetTeamByIdUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(teamId: Int) = repository.searchTeamById(teamId)
}