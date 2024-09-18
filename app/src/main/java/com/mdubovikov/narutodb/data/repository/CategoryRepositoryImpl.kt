package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.domain.entity.Category
import com.mdubovikov.narutodb.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {

    override val categories: Flow<List<Category>> = flowOf(
        listOf(
            Category(0, "Characters", ""),
            Category(1, "Akatsuki", ""),
            Category(2, "Tailed Beasts", ""),
            Category(3, "Kara", ""),
            Category(4, "Kekkei Genkai", ""),
            Category(5, "Teams", ""),
            Category(6, "Clans", ""),
            Category(7, "Villages", "")
        )
    )
}