package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    suspend fun getCharacterById(characterId: Int) = repository.getCharacterById(characterId)

    suspend fun getKaraById(karaId: Int) = repository.getKaraById(karaId)

    suspend fun getTailedBeastById(tailedBeastId: Int) = repository.getTailedBeastById(tailedBeastId)

    suspend fun getAkatsukiById(akatsukiId: Int) = repository.getAkatsukiById(akatsukiId)

}