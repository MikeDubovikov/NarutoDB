package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.data.mapper.toEntity
import com.mdubovikov.narutodb.data.network.api.ApiService
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CharacterRepository {

    override suspend fun getAllCharacters(page: Int, limit: Int): List<Character> {
        return apiService.getAllCharacters(page, limit).characters.toEntity()
    }

    override suspend fun getCharacterById(characterId: Int): Character {
        return apiService.getCharacterById(characterId).toEntity()
    }

    override suspend fun getCharacterByName(name: String): Character {
        return apiService.getCharacterByName(name).toEntity()
    }

    override suspend fun getAllKara(page: Int, limit: Int): List<Character> {
        return apiService.getAllKara(page, limit).kara.toEntity()
    }

    override suspend fun getKaraById(karaId: Int): Character {
        return apiService.getKaraById(karaId).toEntity()
    }

    override suspend fun getKaraByName(name: String): Character {
        return apiService.getKaraByName(name).toEntity()
    }

    override suspend fun getAllTailedBeasts(page: Int, limit: Int): List<Character> {
        return apiService.getAllTailedBeasts(page, limit).tailedBeasts.toEntity()
    }

    override suspend fun getTailedBeastById(tailedBeastId: Int): Character {
        return apiService.getTailedBeastById(tailedBeastId).toEntity()
    }

    override suspend fun getTailedBeastByName(name: String): Character {
        return apiService.getTailedBeastByName(name).toEntity()
    }

    override suspend fun getAllAkatsuki(page: Int, limit: Int): List<Character> {
        return apiService.getAllAkatsuki(page, limit).akatsuki.toEntity()
    }

    override suspend fun getAkatsukiById(akatsukiId: Int): Character {
        return apiService.getAkatsukiById(akatsukiId).toEntity()
    }

    override suspend fun getAkatsukiByName(name: String): Character {
        return apiService.getAkatsukiByName(name).toEntity()
    }

}