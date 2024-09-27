package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.SearchRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    suspend fun getCharacterById(characterId: Int) =
        repository.searchCharacterById(characterId)
}