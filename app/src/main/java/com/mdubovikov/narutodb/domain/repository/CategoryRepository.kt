package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    val categories: Flow<List<Category>>
}