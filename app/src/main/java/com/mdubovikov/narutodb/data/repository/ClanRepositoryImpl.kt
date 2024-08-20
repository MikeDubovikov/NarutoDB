package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.data.mapper.toEntity
import com.mdubovikov.narutodb.data.network.api.ApiService
import com.mdubovikov.narutodb.domain.entity.Clan
import com.mdubovikov.narutodb.domain.repository.ClanRepository
import javax.inject.Inject

class ClanRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ClanRepository {

    override suspend fun getAllClans(page: Int, limit: Int): List<Clan> {
        return apiService.getAllClans(page, limit).clans.toEntity()
    }

    override suspend fun getClanById(clanId: Int): Clan {
        return apiService.getClanById(clanId).toEntity()
    }

    override suspend fun getClanByName(name: String): Clan {
        return apiService.getClanByName(name).toEntity()
    }

}