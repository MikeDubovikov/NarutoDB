package com.mdubovikov.narutodb.data.network.api

import com.mdubovikov.narutodb.data.network.dto.AkatsukiResponse
import com.mdubovikov.narutodb.data.network.dto.CharacterDto
import com.mdubovikov.narutodb.data.network.dto.CharacterResponse
import com.mdubovikov.narutodb.data.network.dto.KaraResponse
import com.mdubovikov.narutodb.data.network.dto.TailedBeastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): CharacterDto

    @GET("character/search")
    suspend fun getCharacterByName(
        @Query("name") name: String
    ): CharacterDto

    @GET("kara")
    suspend fun getAllKara(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<KaraResponse>

    @GET("tailed-beast")
    suspend fun getAllTailedBeasts(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<TailedBeastResponse>

    @GET("akatsuki")
    suspend fun getAllAkatsuki(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<AkatsukiResponse>

}