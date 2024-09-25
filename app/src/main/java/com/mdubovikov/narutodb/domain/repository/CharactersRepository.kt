package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.Character

interface CharactersRepository {

    suspend fun getAllCharacters(page: Int, limit: Int): List<Character>

    suspend fun getAllAkatsuki(page: Int, limit: Int): List<Character>

    suspend fun getAllTailedBeasts(page: Int, limit: Int): List<Character>

    suspend fun getAllKara(page: Int, limit: Int): List<Character>
}