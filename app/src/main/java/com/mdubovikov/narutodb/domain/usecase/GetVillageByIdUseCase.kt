package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.VillageRepository
import javax.inject.Inject

class GetVillageByIdUseCase @Inject constructor(
    private val repository: VillageRepository
) {

    suspend operator fun invoke(villageId: Int) = repository.getVillageById(villageId)

}