package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.KekkeigenkaiRepository
import javax.inject.Inject

class GetKekkeigenkaiByIdUseCase @Inject constructor(
    private val repository: KekkeigenkaiRepository
) {

    suspend operator fun invoke(kekkeigenkaiId: Int) =
        repository.getKekkeigenkaiById(kekkeigenkaiId)

}