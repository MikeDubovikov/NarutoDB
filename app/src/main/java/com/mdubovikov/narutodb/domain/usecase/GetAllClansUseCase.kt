package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.ClanRepository
import javax.inject.Inject

class GetAllClansUseCase @Inject constructor(
    private val repository: ClanRepository
) {

    suspend operator fun invoke() = repository.getAllClans(page = 1, limit = 10)

}