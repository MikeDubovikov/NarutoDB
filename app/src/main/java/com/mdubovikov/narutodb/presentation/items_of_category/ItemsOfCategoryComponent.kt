package com.mdubovikov.narutodb.presentation.items_of_category

import com.mdubovikov.narutodb.domain.entity.ItemOfCategory
import kotlinx.coroutines.flow.StateFlow

interface ItemsOfCategoryComponent {

    val model: StateFlow<ItemsOfCategoryStore.State>

    fun onItemClick(itemOfCategory: ItemOfCategory)

    fun onClickBack()
}