package com.mdubovikov.narutodb.presentation.categories

import com.mdubovikov.narutodb.domain.entity.Category
import kotlinx.coroutines.flow.StateFlow

interface CategoriesComponent {

    val model: StateFlow<CategoriesStore.State>

    fun onCategoryClick(category: Category)
}