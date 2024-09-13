package com.mdubovikov.narutodb.data.repository

import com.mdubovikov.narutodb.domain.entity.Category
import com.mdubovikov.narutodb.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {

    override val categories: Flow<List<Category>> = flowOf()
}