package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.domain.entity.Category
import com.mdubovikov.narutodb.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {

    override val categories: List<Category> = listOf(
        Category(0, "Characters", ""),
        Category(1, "Bookmarks", "")
    )
}