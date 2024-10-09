package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.CharactersRepository
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    suspend fun getAllCharacters() = repository.getAllCharacters()

    suspend fun getAllKara() = repository.getAllKara()

    suspend fun getAllTailedBeasts() = repository.getAllTailedBeasts()

    suspend fun getAllAkatsuki() = repository.getAllAkatsuki()

}