package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.ClanRepository
import javax.inject.Inject

class GetClanByIdUseCase @Inject constructor(
    private val repository: ClanRepository
) {

    suspend operator fun invoke(clanId: Int) = repository.getClanById(clanId)

}