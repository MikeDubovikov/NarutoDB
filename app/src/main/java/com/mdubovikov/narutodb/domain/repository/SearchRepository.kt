package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.CharacterDetails

interface SearchRepository {

    suspend fun searchCharacterByName(characterName: String): CharacterDetails

    suspend fun searchCharacterById(characterId: Int): CharacterDetails
}