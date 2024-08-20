package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.KekkeigenkaiRepository
import javax.inject.Inject

class GetKekkeigenkaiByNameUseCase @Inject constructor(
    private val repository: KekkeigenkaiRepository
) {

    suspend operator fun invoke(name: String) = repository.getKekkeigenkaiByName(name)

}