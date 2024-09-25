package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.CharacterDetails

interface SearchRepository {

    suspend fun searchCharacterByName(characterName: String): CharacterDetails

    suspend fun searchCharacterById(characterId: Int): CharacterDetails

    suspend fun searchAkatsukiByName(akatsukiName: String): CharacterDetails

    suspend fun searchAkatsukiById(akatsukiId: Int): CharacterDetails

    suspend fun searchTailedBeastByName(tailedBeastName: String): CharacterDetails

    suspend fun searchTailedBeastById(tailedBeastId: Int): CharacterDetails

    suspend fun searchKaraByName(karaName: String): CharacterDetails

    suspend fun searchKaraById(karaId: Int): CharacterDetails
}