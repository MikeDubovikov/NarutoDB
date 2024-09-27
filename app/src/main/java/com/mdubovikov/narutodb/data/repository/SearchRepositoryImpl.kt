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
}