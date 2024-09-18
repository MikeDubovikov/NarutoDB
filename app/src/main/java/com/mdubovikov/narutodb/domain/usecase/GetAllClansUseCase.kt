package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.CategoriesRepository
import javax.inject.Inject

class GetAllClansUseCase @Inject constructor(
    private val repository: CategoriesRepository
) {

    suspend operator fun invoke() = repository.getAllClans(page = 1, limit = 10)
}