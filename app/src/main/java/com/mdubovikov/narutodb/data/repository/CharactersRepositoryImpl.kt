package com.mdubovikov.narutodb.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mdubovikov.narutodb.data.network.CharactersPageSource
import com.mdubovikov.narutodb.data.network.api.ApiService
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.domain.repository.CharactersRepository
import com.mdubovikov.narutodb.presentation.characters.CharacterOptions
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CharactersRepository {

    override suspend fun getAllCharacters(): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { CharactersPageSource(apiService, CharacterOptions.AllCharacters) }
    ).flow

    override suspend fun getAllAkatsuki(): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { CharactersPageSource(apiService, CharacterOptions.AllAkatsuki) }
    ).flow

    override suspend fun getAllTailedBeasts(): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { CharactersPageSource(apiService, CharacterOptions.AllTailedBeasts) }
    ).flow

    override suspend fun getAllKara(): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { CharactersPageSource(apiService, CharacterOptions.AllKara) }
    ).flow
}