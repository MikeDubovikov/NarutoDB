package com.mdubovikov.narutodb.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.mdubovikov.narutodb.domain.entity.Category
import com.mdubovikov.narutodb.domain.entity.ItemOfCategory
import com.mdubovikov.narutodb.presentation.categories.DefaultCategoriesComponent
import com.mdubovikov.narutodb.presentation.characters.DefaultCharactersComponent
import com.mdubovikov.narutodb.presentation.details.DefaultDetailsComponent
import com.mdubovikov.narutodb.presentation.items_of_category.DefaultItemsOfCategoryComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable

class DefaultRootComponent @AssistedInject constructor(
    private val categoriesComponentFactory: DefaultCategoriesComponent.Factory,
    private val charactersComponentFactory: DefaultCharactersComponent.Factory,
    private val detailsComponentFactory: DefaultDetailsComponent.Factory,
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
                    onCategoryClick = { category ->
                        navigation.push(Config.ItemOfCategories(category = category))
                    onCharactersClicked = {
                        navigation.push(Config.Characters)
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Categories(component)
            }

            Config.Characters -> {
                val component = charactersComponentFactory.create(
                    onCharacterClicked = { character ->
                        navigation.push(Config.Details(character))
                    },
                    onBackClicked = { navigation.pop() },
                    componentContext = componentContext
                )
                RootComponent.Child.Characters(component)
            }

            is Config.Details -> {
                val component = detailsComponentFactory.create(
                    character = config.character,
                    onBackClicked = {
                        navigation.pop()
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Details(component)
            }
        }
    }

    @Serializable
    sealed interface Config {

        @Serializable
        data object Categories : Config

        @Serializable
        data object Characters : Config

        @Serializable
        data class Details(val itemOfCategory: ItemOfCategory) : Config
        data class Details(val character: Character) : Config
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultRootComponent
    }
}