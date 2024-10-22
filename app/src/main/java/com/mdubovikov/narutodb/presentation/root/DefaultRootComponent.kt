package com.mdubovikov.narutodb.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.mdubovikov.narutodb.domain.entity.Category
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.presentation.bookmarks.DefaultBookmarksComponent
import com.mdubovikov.narutodb.presentation.categories.DefaultCategoriesComponent
import com.mdubovikov.narutodb.presentation.characters.DefaultCharactersComponent
import com.mdubovikov.narutodb.presentation.details.DefaultDetailsComponent
import com.mdubovikov.narutodb.presentation.search.DefaultSearchComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable

class DefaultRootComponent @AssistedInject constructor(
    private val categoriesComponentFactory: DefaultCategoriesComponent.Factory,
    private val charactersComponentFactory: DefaultCharactersComponent.Factory,
    private val detailsComponentFactory: DefaultDetailsComponent.Factory,
    private val bookmarksComponentFactory: DefaultBookmarksComponent.Factory,
    private val searchComponentFactory: DefaultSearchComponent.Factory,
    @Assisted("componentContext") componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Categories,
        handleBackButton = true,
        childFactory = ::child,
        serializer = Config.serializer()
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            Config.Categories -> {
                val component = categoriesComponentFactory.create(
                    onCategoryClicked = { category ->
                        when (category.id) {
                            0 -> navigation.push(Config.Characters(category))
                            1 -> navigation.push(Config.Bookmarks(category))
                        }

                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Categories(component)
            }

            is Config.Characters -> {
                val component = charactersComponentFactory.create(
                    category = config.category,
                    onCharacterClicked = { character ->
                        navigation.push(Config.Details(character))
                    },
                    onBackClicked = { navigation.pop() },
                    onSearchClicked = { navigation.push(Config.Search) },
                    componentContext = componentContext
                )
                RootComponent.Child.Characters(component)
            }

            is Config.Details -> {
                val component = detailsComponentFactory.create(
                    character = config.character,
                    onBackClicked = { navigation.pop() },
                    componentContext = componentContext
                )
                RootComponent.Child.Details(component)
            }

            is Config.Bookmarks -> {
                val component = bookmarksComponentFactory.create(
                    category = config.category,
                    onBookmarkClicked = { character ->
                        navigation.push(Config.Details(character))
                    },
                    onBackClicked = { navigation.pop() },
                    componentContext = componentContext
                )
                RootComponent.Child.Bookmarks(component)
            }

            is Config.Search -> {
                val component = searchComponentFactory.create(
                    searchCharacter = { character ->
                        navigation.push(Config.Details(character))
                    },
                    onBackClicked = { navigation.pop() },
                    componentContext = componentContext
                )
                RootComponent.Child.Search(component)
            }
        }
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object Categories : Config

        @Serializable
        data class Characters(val category: Category) : Config

        @Serializable
        data class Details(val character: Character) : Config

        @Serializable
        data class Bookmarks(val category: Category) : Config

        @Serializable
        data object Search : Config
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultRootComponent
    }
}