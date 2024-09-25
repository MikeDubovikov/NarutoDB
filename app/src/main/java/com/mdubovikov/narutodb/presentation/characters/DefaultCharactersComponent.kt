package com.mdubovikov.narutodb.presentation.characters

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.presentation.extension.componentScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DefaultCharactersComponent @AssistedInject constructor(
    private val storeFactory: CharactersStoreFactory,
    @Assisted("onCharacterClicked") private val onCharacterClicked: (Character) -> Unit,
    @Assisted("onBackClicked") private val onBackClicked: () -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : CharactersComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    CharactersStore.Label.ClickBack -> {
                        onBackClicked()
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

    override fun onCharacterClick(character: Character) {
        store.accept(CharactersStore.Intent.CharacterClick(character))
    }

    override fun onClickBack() {
        store.accept(CharactersStore.Intent.ClickBack)
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("onCharacterClicked") onCharacterClicked: (Character) -> Unit,
            @Assisted("onBackClicked") onBackClicked: () -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultCharactersComponent
    }
}