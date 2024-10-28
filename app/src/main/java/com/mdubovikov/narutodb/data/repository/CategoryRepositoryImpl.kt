package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.R
import com.mdubovikov.narutodb.domain.entity.Category
import com.mdubovikov.narutodb.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {
    override val categories: List<Category> = listOf(
        Category(
            id = 0,
            name = "Characters",
            description = "Discover known characters",
            image = R.drawable.all_characters.toString()
        ),
        Category(
            id = 1,
            name = "Bookmarks",
            description = "Explore favourite characters",
            image = R.drawable.favourite_characters.toString()
        )
    )
}