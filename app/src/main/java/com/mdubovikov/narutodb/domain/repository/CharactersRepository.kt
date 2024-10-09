package com.mdubovikov.narutodb.domain.repository

import androidx.paging.PagingData
import com.mdubovikov.narutodb.domain.entity.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    suspend fun getAllCharacters(): Flow<PagingData<Character>>

    suspend fun getAllAkatsuki(): Flow<PagingData<Character>>

    suspend fun getAllTailedBeasts(): Flow<PagingData<Character>>

    suspend fun getAllKara(): Flow<PagingData<Character>>
}