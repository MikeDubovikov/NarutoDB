package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.Character

interface CharacterRepository {

    suspend fun getAllCharacters(page: Int, limit: Int): List<Character>

    suspend fun getCharacterById(characterId: Int): Character

    suspend fun getCharacterByName(name: String): Character

    suspend fun getAllKara(page: Int, limit: Int): List<Character>

    suspend fun getKaraById(karaId: Int): Character

    suspend fun getKaraByName(name: String): Character

    suspend fun getAllTailedBeasts(page: Int, limit: Int): List<Character>

    suspend fun getTailedBeastById(tailedBeastId: Int): Character

    suspend fun getTailedBeastByName(name: String): Character

    suspend fun getAllAkatsuki(page: Int, limit: Int): List<Character>

    suspend fun getAkatsukiById(akatsukiId: Int): Character

    suspend fun getAkatsukiByName(name: String): Character

}