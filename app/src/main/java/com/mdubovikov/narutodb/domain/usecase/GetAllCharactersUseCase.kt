package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.CharacterRepository
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    suspend fun getAllCharacters() = repository.getAllCharacters(page = 1, limit = 10)

    suspend fun getAllKara() = repository.getAllKara(page = 1, limit = 10)

    suspend fun getAllTailedBeasts() = repository.getAllTailedBeasts(page = 1, limit = 10)

    suspend fun getAllAkatsuki() = repository.getAllAkatsuki(page = 1, limit = 10)

}