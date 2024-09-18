package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.SearchRepository
import javax.inject.Inject

class GetClanByIdUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(clanId: Int) = repository.searchClanById(clanId)
}