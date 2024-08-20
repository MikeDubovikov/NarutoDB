package com.mdubovikov.narutodb.data.network.api

import com.mdubovikov.narutodb.data.network.dto.AkatsukiResponse
import com.mdubovikov.narutodb.data.network.dto.CharacterDto
import com.mdubovikov.narutodb.data.network.dto.CharacterResponse
import com.mdubovikov.narutodb.data.network.dto.ClanDto
import com.mdubovikov.narutodb.data.network.dto.ClanResponse
import com.mdubovikov.narutodb.data.network.dto.KaraResponse
import com.mdubovikov.narutodb.data.network.dto.KekkeigenkaiDto
import com.mdubovikov.narutodb.data.network.dto.KekkeigenkaiResponse
import com.mdubovikov.narutodb.data.network.dto.TailedBeastResponse
import com.mdubovikov.narutodb.data.network.dto.TeamDto
import com.mdubovikov.narutodb.data.network.dto.TeamResponse
import com.mdubovikov.narutodb.data.network.dto.VillageDto
import com.mdubovikov.narutodb.data.network.dto.VillageResponse
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

    @GET("clan")
    suspend fun getAllClans(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): ClanResponse

    @GET("clan/{id}")
    suspend fun getClanById(
        @Path("id") id: Int
    ): ClanDto

    @GET("clan/search")
    suspend fun getClanByName(
        @Query("name") name: String
    ): ClanDto

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

    @GET("kekkei-genkai")
    suspend fun getAllKekkeigenkai(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): KekkeigenkaiResponse

    @GET("kekkei-genkai/{id}")
    suspend fun getKekkeigenkaiById(
        @Path("id") id: Int
    ): KekkeigenkaiDto

    @GET("kekkei-genkai/search")
    suspend fun getKekkeigenkaiByName(
        @Query("name") name: String
    ): KekkeigenkaiDto

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

    @GET("team")
    suspend fun getAllTeams(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): TeamResponse

    @GET("team/{id}")
    suspend fun getTeamById(
        @Path("id") id: Int
    ): TeamDto

    @GET("team/search")
    suspend fun getTeamByName(
        @Query("name") name: String
    ): TeamDto

    @GET("village")
    suspend fun getAllVillages(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): VillageResponse

    @GET("village/{id}")
    suspend fun getVillageById(
        @Path("id") id: Int
    ): VillageDto

    @GET("village/search")
    suspend fun getVillageByName(
        @Query("name") name: String
    ): VillageDto

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