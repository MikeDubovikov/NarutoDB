package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.VillageRepository
import javax.inject.Inject

class GetAllVillageUseCase @Inject constructor(
    private val repository: VillageRepository
) {

    suspend operator fun invoke() = repository.getAllVillages(page = 1, limit = 10)

}