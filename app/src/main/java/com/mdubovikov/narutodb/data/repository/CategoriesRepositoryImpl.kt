package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.data.mapper.toEntityList
import com.mdubovikov.narutodb.data.network.api.ApiService
import com.mdubovikov.narutodb.domain.entity.ItemOfCategory
import com.mdubovikov.narutodb.domain.repository.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CategoriesRepository {

    override suspend fun getAllCharacters(page: Int, limit: Int): List<ItemOfCategory> {
        return apiService.getAllCharacters(page, limit).characters.toEntityList()
    }

    override suspend fun getAllAkatsuki(page: Int, limit: Int): List<ItemOfCategory> {
        return apiService.getAllAkatsuki(page, limit).akatsuki.toEntityList()
    }

    override suspend fun getAllTailedBeasts(page: Int, limit: Int): List<ItemOfCategory> {
        return apiService.getAllTailedBeasts(page, limit).tailedBeasts.toEntityList()
    }

    override suspend fun getAllKara(page: Int, limit: Int): List<ItemOfCategory> {
        return apiService.getAllKara(page, limit).kara.toEntityList()
    }

    override suspend fun getAllKekkeigenkai(page: Int, limit: Int): List<ItemOfCategory> {
        return apiService.getAllKekkeigenkai(page, limit).kekkeigenkai.toEntityList()
    }

    override suspend fun getAllClans(page: Int, limit: Int): List<ItemOfCategory> {
        return apiService.getAllClans(page, limit).clans.toEntityList()
    }

    override suspend fun getAllTeams(page: Int, limit: Int): List<ItemOfCategory> {
        return apiService.getAllTeams(page, limit).teams.toEntityList()
    }

    override suspend fun getAllVillages(page: Int, limit: Int): List<ItemOfCategory> {
        return apiService.getAllVillages(page, limit).villages.toEntityList()
    }
}