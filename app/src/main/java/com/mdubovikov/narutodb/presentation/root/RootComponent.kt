package com.mdubovikov.narutodb.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.mdubovikov.narutodb.presentation.bookmarks.BookmarksComponent
import com.mdubovikov.narutodb.presentation.categories.CategoriesComponent
import com.mdubovikov.narutodb.presentation.characters.CharactersComponent
import com.mdubovikov.narutodb.presentation.details.DetailsComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        data class Categories(val component: CategoriesComponent) : Child
        data class Characters(val component: CharactersComponent) : Child
        data class Details(val component: DetailsComponent) : Child
        data class Bookmarks(val component: BookmarksComponent) : Child
    }
}