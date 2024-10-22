package com.mdubovikov.narutodb.presentation.characters

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.mdubovikov.narutodb.domain.entity.Category
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.presentation.common.componentScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DefaultCharactersComponent @AssistedInject constructor(
    private val storeFactory: CharactersStoreFactory,
    @Assisted("category") private val category: Category,
    @Assisted("onCharacterClicked") private val onCharacterClicked: (Character) -> Unit,
    @Assisted("onBackClicked") private val onBackClicked: () -> Unit,
    @Assisted("onSearchClicked") private val onSearchClicked: () -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : CharactersComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create(category) }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    CharactersStore.Label.ClickBack -> {
                        onBackClicked()
                    }

                    CharactersStore.Label.ClickSearch -> {
                        onSearchClicked()
                    }

                    is CharactersStore.Label.CharacterClick -> {
                        onCharacterClicked(it.character)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<CharactersStore.State> = store.stateFlow

    override fun changeCharacterOption(option: CharacterOptions) {
        store.accept(CharactersStore.Intent.ChangeCharacterOptions(option))
    }

    override fun onCharacterClick(character: Character) {
        store.accept(CharactersStore.Intent.CharacterClick(character))
    }

    override fun onClickBack() {
        store.accept(CharactersStore.Intent.ClickBack)
    }

    override fun onClickSearch() {
        store.accept(CharactersStore.Intent.ClickSearch)
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("category") category: Category,
            @Assisted("onCharacterClicked") onCharacterClicked: (Character) -> Unit,
            @Assisted("onBackClicked") onBackClicked: () -> Unit,
            @Assisted("onSearchClicked") onSearchClicked: () -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultCharactersComponent
    }
}