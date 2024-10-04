package com.mdubovikov.narutodb.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mdubovikov.narutodb.data.mapper.toCharactersList
import com.mdubovikov.narutodb.data.network.api.ApiService
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.presentation.characters.CharacterOptions
import retrofit2.HttpException
import javax.inject.Inject

class CharactersPageSource @Inject constructor(
    private val apiService: ApiService,
    private val characterOptions: CharacterOptions
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {

        val page = params.key ?: 1
        val pageSize = params.loadSize
        when (characterOptions) {
            CharacterOptions.AllCharacters -> {
                val response = apiService.getAllCharacters(page, pageSize)
                if (response.isSuccessful) {
                    val characters = checkNotNull(response.body()).characters.toCharactersList()
                    val nextKey = if (characters.size < pageSize) null else page + 1
                    val prevKey = if (page == 1) null else page - 1
                    return LoadResult.Page(characters, prevKey, nextKey)
                } else {
                    return LoadResult.Error(HttpException(response))
                }
            }

            CharacterOptions.AllAkatsuki -> {
                val response = apiService.getAllAkatsuki(page, pageSize)
                if (response.isSuccessful) {
                    val characters = checkNotNull(response.body()).akatsuki.toCharactersList()
                    val nextKey = if (characters.size < pageSize) null else page + 1
                    val prevKey = if (page == 1) null else page - 1
                    return LoadResult.Page(characters, prevKey, nextKey)
                } else {
                    return LoadResult.Error(HttpException(response))
                }
            }

            CharacterOptions.AllTailedBeasts -> {
                val response = apiService.getAllTailedBeasts(page, pageSize)
                if (response.isSuccessful) {
                    val characters = checkNotNull(response.body()).tailedBeasts.toCharactersList()
                    val nextKey = if (characters.size < pageSize) null else page + 1
                    val prevKey = if (page == 1) null else page - 1
                    return LoadResult.Page(characters, prevKey, nextKey)
                } else {
                    return LoadResult.Error(HttpException(response))
                }
            }

            CharacterOptions.AllKara -> {
                val response = apiService.getAllKara(page, pageSize)
                if (response.isSuccessful) {
                    val characters = checkNotNull(response.body()).kara.toCharactersList()
                    val nextKey = if (characters.size < pageSize) null else page + 1
                    val prevKey = if (page == 1) null else page - 1
                    return LoadResult.Page(characters, prevKey, nextKey)
                } else {
                    return LoadResult.Error(HttpException(response))
                }
            }
        }
    }
}