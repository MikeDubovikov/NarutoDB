package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterByNameUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    suspend fun getCharacterByName(name: String) = repository.getCharacterByName(name)

    suspend fun getKaraByName(name: String) = repository.getKaraByName(name)

    suspend fun getTailedBeastByName(name: String) = repository.getTailedBeastByName(name)

    suspend fun getAkatsukiByName(name: String) = repository.getAkatsukiByName(name)

}