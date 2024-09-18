package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.SearchRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    suspend fun getCharacterById(characterId: Int) =
        repository.searchCharacterById(characterId)

    suspend fun getKaraById(karaId: Int) =
        repository.searchCharacterById(karaId)

    suspend fun getTailedBeastById(tailedBeastId: Int) =
        repository.searchCharacterById(tailedBeastId)

    suspend fun getAkatsukiById(akatsukiId: Int) =
        repository.searchCharacterById(akatsukiId)

}