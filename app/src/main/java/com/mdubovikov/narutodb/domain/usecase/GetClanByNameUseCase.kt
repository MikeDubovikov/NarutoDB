package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.ClanRepository
import javax.inject.Inject

class GetClanByNameUseCase @Inject constructor(
    private val repository: ClanRepository
) {

    suspend operator fun invoke(name: String) = repository.getClanByName(name)

}