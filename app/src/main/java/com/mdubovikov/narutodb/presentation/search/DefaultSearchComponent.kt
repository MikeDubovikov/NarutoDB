package com.mdubovikov.narutodb.presentation.search

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.presentation.common.componentScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DefaultSearchComponent @AssistedInject constructor(
    private val storeFactory: SearchStoreFactory,
    @Assisted("onBackClicked") private val onBackClicked: () -> Unit,
    @Assisted("searchCharacter") private val searchCharacter: (Character) -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : SearchComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    is SearchStore.Label.SearchCharacter -> {
                        searchCharacter(it.character)
                    }

                    SearchStore.Label.ClickBack -> onBackClicked()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<SearchStore.State> = store.stateFlow

    override fun changeSearchQuery(query: String) {
        store.accept(SearchStore.Intent.ChangeSearchQuery(query))
    }

    override fun searchCharacter() {
        store.accept(SearchStore.Intent.SearchCharacter)
    }

    override fun onClickBack() {
        store.accept(SearchStore.Intent.ClickBack)
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("onBackClicked") onBackClicked: () -> Unit,
            @Assisted("searchCharacter") searchCharacter: (Character) -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultSearchComponent
    }
}