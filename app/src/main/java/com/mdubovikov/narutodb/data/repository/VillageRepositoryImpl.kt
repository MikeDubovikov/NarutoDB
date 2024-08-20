package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.data.mapper.toEntity
import com.mdubovikov.narutodb.data.network.api.ApiService
import com.mdubovikov.narutodb.domain.entity.Village
import com.mdubovikov.narutodb.domain.repository.VillageRepository
import javax.inject.Inject

class VillageRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : VillageRepository {

    override suspend fun getAllVillages(page: Int, limit: Int): List<Village> {
        return apiService.getAllVillages(page, limit).villages.toEntity()
    }

    override suspend fun getVillageById(villageId: Int): Village {
        return apiService.getVillageById(villageId).toEntity()
    }

    override suspend fun getVillageByName(name: String): Village {
        return apiService.getVillageByName(name).toEntity()
    }

}