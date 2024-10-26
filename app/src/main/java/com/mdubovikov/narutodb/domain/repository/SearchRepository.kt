package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.CharacterDetails
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    val recentQueries: Flow<List<String>>
    suspend fun saveQuery(query: String)
    suspend fun searchCharacterByName(characterName: String): CharacterDetails
    suspend fun searchCharacterById(characterId: Int): CharacterDetails
}