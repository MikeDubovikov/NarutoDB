package com.mdubovikov.narutodb.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.mdubovikov.narutodb.presentation.categories.CategoriesComponent
import com.mdubovikov.narutodb.presentation.details.DetailsComponent
import com.mdubovikov.narutodb.presentation.items_of_category.ItemsOfCategoryComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {

        data class Categories(val component: CategoriesComponent) : Child

        data class ItemsOfCategory(val component: ItemsOfCategoryComponent) : Child

        data class Details(val component: DetailsComponent) : Child
    }
}