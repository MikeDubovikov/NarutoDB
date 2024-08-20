package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.VillageRepository
import javax.inject.Inject

class GetVillageByNameUseCase @Inject constructor(
    private val repository: VillageRepository
) {

    suspend operator fun invoke(name: String) = repository.getVillageByName(name)

}