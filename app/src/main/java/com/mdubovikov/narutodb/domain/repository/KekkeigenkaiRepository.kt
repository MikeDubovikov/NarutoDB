package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.Kekkeigenkai

interface KekkeigenkaiRepository {

    suspend fun getAllKekkeigenkai(page: Int, limit: Int): List<Kekkeigenkai>

    suspend fun getKekkeigenkaiById(kekkeigenkaiId: Int): Kekkeigenkai

    suspend fun getKekkeigenkaiByName(name: String): Kekkeigenkai

}