package com.mdubovikov.narutodb.domain.usecase

import com.mdubovikov.narutodb.domain.repository.SearchRepository
import javax.inject.Inject

class GetCharacterByNameUseCase @Inject constructor(
    private val repository: SearchRepository
) {

    suspend fun getCharacterByName(characterName: String) =
        repository.searchCharacterByName(characterName)

    suspend fun getKaraByName(karaName: String) =
        repository.searchCharacterByName(karaName)

    suspend fun getTailedBeastByName(tailedBeastName: String) =
        repository.searchCharacterByName(tailedBeastName)

    suspend fun getAkatsukiByName(akatsukiName: String) =
        repository.searchCharacterByName(akatsukiName)
}