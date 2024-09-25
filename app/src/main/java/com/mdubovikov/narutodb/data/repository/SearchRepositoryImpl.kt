package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.data.mapper.toCharacterDetails
import com.mdubovikov.narutodb.data.network.api.ApiService
import com.mdubovikov.narutodb.domain.entity.CharacterDetails
import com.mdubovikov.narutodb.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SearchRepository {

    override suspend fun searchCharacterByName(characterName: String): CharacterDetails {
        return apiService.getCharacterByName(characterName).toCharacterDetails()
    }

    override suspend fun searchCharacterById(characterId: Int): CharacterDetails {
        return apiService.getCharacterById(characterId).toCharacterDetails()
    }

    override suspend fun searchAkatsukiByName(akatsukiName: String): CharacterDetails {
        return apiService.getCharacterByName(akatsukiName).toCharacterDetails()
    }

    override suspend fun searchAkatsukiById(akatsukiId: Int): CharacterDetails {
        return apiService.getCharacterById(akatsukiId).toCharacterDetails()
    }

    override suspend fun searchTailedBeastByName(tailedBeastName: String): CharacterDetails {
        return apiService.getCharacterByName(tailedBeastName).toCharacterDetails()
    }

    override suspend fun searchTailedBeastById(tailedBeastId: Int): CharacterDetails {
        return apiService.getCharacterById(tailedBeastId).toCharacterDetails()
    }

    override suspend fun searchKaraByName(karaName: String): CharacterDetails {
        return apiService.getCharacterByName(karaName).toCharacterDetails()
    }

    override suspend fun searchKaraById(karaId: Int): CharacterDetails {
        return apiService.getCharacterById(karaId).toCharacterDetails()
    }
}