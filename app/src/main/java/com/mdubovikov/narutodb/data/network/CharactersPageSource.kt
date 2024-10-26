package com.mdubovikov.narutodb.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mdubovikov.narutodb.data.mapper.toCharactersList
import com.mdubovikov.narutodb.data.network.api.ApiService
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.presentation.characters.CharacterOptions
import retrofit2.HttpException
import java.io.IOException
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
                return try {
                    val response = apiService.getAllCharacters(page, pageSize)
                    val characters = checkNotNull(response.body()).characters.toCharactersList()
                    val nextKey = if (characters.size < pageSize) null else page + 1
                    val prevKey = if (page == 1) null else page - 1

                    LoadResult.Page(
                        data = characters,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                } catch (e: IOException) {
                    LoadResult.Error(e)
                } catch (e: HttpException) {
                    LoadResult.Error(e)
                }
            }

            CharacterOptions.AllAkatsuki -> {
                return try {
                    val response = apiService.getAllAkatsuki(page, pageSize)
                    val characters = checkNotNull(response.body()).akatsuki.toCharactersList()
                    val nextKey = if (characters.size < pageSize) null else page + 1
                    val prevKey = if (page == 1) null else page - 1

                    LoadResult.Page(
                        data = characters,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                } catch (e: IOException) {
                    LoadResult.Error(e)
                } catch (e: HttpException) {
                    LoadResult.Error(e)
                }
            }

            CharacterOptions.AllTailedBeasts -> {
                return try {
                    val response = apiService.getAllTailedBeasts(page, pageSize)
                    val characters = checkNotNull(response.body()).tailedBeasts.toCharactersList()
                    val nextKey = if (characters.size < pageSize) null else page + 1
                    val prevKey = if (page == 1) null else page - 1

                    LoadResult.Page(
                        data = characters,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                } catch (e: IOException) {
                    LoadResult.Error(e)
                } catch (e: HttpException) {
                    LoadResult.Error(e)
                }
            }

            CharacterOptions.AllKara -> {
                return try {
                    val response = apiService.getAllKara(page, pageSize)
                    val characters = checkNotNull(response.body()).kara.toCharactersList()
                    val nextKey = if (characters.size < pageSize) null else page + 1
                    val prevKey = if (page == 1) null else page - 1

                    LoadResult.Page(
                        data = characters,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                } catch (e: IOException) {
                    LoadResult.Error(e)
                } catch (e: HttpException) {
                    LoadResult.Error(e)
                }
            }
        }
    }
}