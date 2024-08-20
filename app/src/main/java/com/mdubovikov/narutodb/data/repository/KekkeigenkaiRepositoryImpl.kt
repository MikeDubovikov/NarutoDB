package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.data.mapper.toEntity
import com.mdubovikov.narutodb.data.network.api.ApiService
import com.mdubovikov.narutodb.domain.entity.Kekkeigenkai
import com.mdubovikov.narutodb.domain.repository.KekkeigenkaiRepository
import javax.inject.Inject

class KekkeigenkaiRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : KekkeigenkaiRepository {

    override suspend fun getAllKekkeigenkai(page: Int, limit: Int): List<Kekkeigenkai> {
        return apiService.getAllKekkeigenkai(page, limit).kekkeigenkai.toEntity()
    }

    override suspend fun getKekkeigenkaiById(kekkeigenkaiId: Int): Kekkeigenkai {
        return apiService.getKekkeigenkaiById(kekkeigenkaiId).toEntity()
    }

    override suspend fun getKekkeigenkaiByName(name: String): Kekkeigenkai {
        return apiService.getKekkeigenkaiByName(name).toEntity()
    }

}