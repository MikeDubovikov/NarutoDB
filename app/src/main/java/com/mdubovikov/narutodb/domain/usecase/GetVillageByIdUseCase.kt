package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.SearchRepository
import javax.inject.Inject

class GetVillageByIdUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(villageId: Int) = repository.searchVillageById(villageId)
}