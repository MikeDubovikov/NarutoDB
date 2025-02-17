package com.mdubovikov.narutodb.presentation.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.mdubovikov.narutodb.presentation.bookmarks.BookmarksContent
import com.mdubovikov.narutodb.presentation.categories.CategoriesContent
import com.mdubovikov.narutodb.presentation.characters.CharactersContent
import com.mdubovikov.narutodb.presentation.details.DetailsContent
import com.mdubovikov.narutodb.presentation.ui.theme.NarutoDBTheme

@Composable
fun RootContent(component: RootComponent) {
    NarutoDBTheme {
        Children(
            stack = component.stack,
            animation = stackAnimation(slide())
        ) {
            when (val instance = it.instance) {
                is RootComponent.Child.Categories -> {
                    CategoriesContent(component = instance.component)
                }

                is RootComponent.Child.Characters -> {
                    CharactersContent(component = instance.component)
                }

                is RootComponent.Child.Details -> {
                    DetailsContent(component = instance.component)
                }

                is RootComponent.Child.Bookmarks -> {
                    BookmarksContent(component = instance.component)
                }
            }
        }
    }
}