package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.Village

interface VillageRepository {

    suspend fun getAllVillages(page: Int, limit: Int): List<Village>

    suspend fun getVillageById(villageId: Int): Village

    suspend fun getVillageByName(name: String): Village

}