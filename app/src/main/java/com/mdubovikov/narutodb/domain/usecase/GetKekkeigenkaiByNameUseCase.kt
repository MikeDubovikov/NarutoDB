package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.SearchRepository
import javax.inject.Inject

class GetKekkeigenkaiByNameUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    suspend operator fun invoke(name: String) = repository.searchKekkeigenkaiByName(name)
}