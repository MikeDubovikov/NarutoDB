package com.mdubovikov.narutodb.domain.repository

import com.mdubovikov.narutodb.domain.entity.Category

interface CategoryRepository {
    val categories: List<Category>
}