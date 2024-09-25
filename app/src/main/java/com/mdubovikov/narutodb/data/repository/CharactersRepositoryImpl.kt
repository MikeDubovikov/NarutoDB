package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.data.mapper.toCharactersList
import com.mdubovikov.narutodb.data.network.api.ApiService
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CharactersRepository {

    override suspend fun getAllCharacters(page: Int, limit: Int): List<Character> {
        return apiService.getAllCharacters(page, limit).characters.toCharactersList()
    }

    override suspend fun getAllAkatsuki(page: Int, limit: Int): List<Character> {
        return apiService.getAllAkatsuki(page, limit).akatsuki.toCharactersList()
    }

    override suspend fun getAllTailedBeasts(page: Int, limit: Int): List<Character> {
        return apiService.getAllTailedBeasts(page, limit).tailedBeasts.toCharactersList()
    }

    override suspend fun getAllKara(page: Int, limit: Int): List<Character> {
        return apiService.getAllKara(page, limit).kara.toCharactersList()
    }
}