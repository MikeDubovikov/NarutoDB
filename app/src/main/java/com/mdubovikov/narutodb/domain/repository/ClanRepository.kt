package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.Clan

interface ClanRepository {

    suspend fun getAllClans(page: Int, limit: Int): List<Clan>

    suspend fun getClanById(clanId: Int): Clan

    suspend fun getClanByName(name: String): Clan

}