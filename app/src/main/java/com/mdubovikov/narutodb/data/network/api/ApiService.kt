package com.mdubovikov.narutodb.data.network.api

import com.mdubovikov.narutodb.data.network.dto.AkatsukiResponse
import com.mdubovikov.narutodb.data.network.dto.CharacterDto
import com.mdubovikov.narutodb.data.network.dto.CharacterResponse
import com.mdubovikov.narutodb.data.network.dto.KaraResponse
import com.mdubovikov.narutodb.data.network.dto.TailedBeastResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): CharacterResponse

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
    ): KaraResponse

    @GET("kara/{id}")
    suspend fun getKaraById(
        @Path("id") id: Int
    ): CharacterDto

    @GET("kara/search")
    suspend fun getKaraByName(
        @Query("name") name: String
    ): CharacterDto

    @GET("tailed-beast")
    suspend fun getAllTailedBeasts(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): TailedBeastResponse

    @GET("tailed-beast/{id}")
    suspend fun getTailedBeastById(
        @Path("id") id: Int
    ): CharacterDto

    @GET("tailed-beast/search")
    suspend fun getTailedBeastByName(
        @Query("name") name: String
    ): CharacterDto

    @GET("akatsuki")
    suspend fun getAllAkatsuki(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): AkatsukiResponse

    @GET("akatsuki/{id}")
    suspend fun getAkatsukiById(
        @Path("id") id: Int
    ): CharacterDto

    @GET("akatsuki/search")
    suspend fun getAkatsukiByName(
        @Query("name") name: String
    ): CharacterDto

}