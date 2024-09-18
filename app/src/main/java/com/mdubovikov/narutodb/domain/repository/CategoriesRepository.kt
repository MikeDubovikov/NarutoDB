package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.ItemOfCategory

interface CategoriesRepository {

    suspend fun getAllCharacters(page: Int, limit: Int): List<ItemOfCategory>

    suspend fun getAllAkatsuki(page: Int, limit: Int): List<ItemOfCategory>

    suspend fun getAllTailedBeasts(page: Int, limit: Int): List<ItemOfCategory>

    suspend fun getAllKara(page: Int, limit: Int): List<ItemOfCategory>

    suspend fun getAllKekkeigenkai(page: Int, limit: Int): List<ItemOfCategory>

    suspend fun getAllClans(page: Int, limit: Int): List<ItemOfCategory>

    suspend fun getAllTeams(page: Int, limit: Int): List<ItemOfCategory>

    suspend fun getAllVillages(page: Int, limit: Int): List<ItemOfCategory>
}