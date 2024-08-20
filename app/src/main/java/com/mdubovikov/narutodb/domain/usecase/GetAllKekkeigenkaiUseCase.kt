package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.KekkeigenkaiRepository
import javax.inject.Inject

class GetAllKekkeigenkaiUseCase @Inject constructor(
    private val repository: KekkeigenkaiRepository
) {

    suspend operator fun invoke() = repository.getAllKekkeigenkai(page = 1, limit = 10)

}