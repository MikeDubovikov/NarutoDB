package com.mdubovikov.narutodb.presentation.search

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.mdubovikov.narutodb.data.mapper.toCharacter
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.domain.usecase.GetCharacterByNameUseCase
import com.mdubovikov.narutodb.presentation.search.SearchStore.Intent
import com.mdubovikov.narutodb.presentation.search.SearchStore.Label
import com.mdubovikov.narutodb.presentation.search.SearchStore.State
import kotlinx.coroutines.launch
import javax.inject.Inject

interface SearchStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data class ChangeSearchQuery(val query: String) : Intent
        data object ClickBack : Intent
        data object SearchCharacter : Intent
    }

    data class State(
        val searchQuery: String,
        val recentQueries: List<String>,
        val isNotFound: Boolean
    )

    sealed interface Label {
        data object ClickBack : Label
        data class SearchCharacter(val character: Character) : Label
    }
}

class SearchStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getCharacterByNameUseCase: GetCharacterByNameUseCase
) {

    fun create(): SearchStore =
        object : SearchStore, Store<Intent, State, Label> by storeFactory.create(
            name = "SearchStore",
            initialState = State(
                searchQuery = "",
                recentQueries = emptyList(),
                isNotFound = false
            ),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}


    private sealed interface Msg {
        data class ChangeSearchQuery(val query: String) : Msg
        data object SearchNotFound : Msg
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Nothing, State, Msg, Label>() {
        override fun executeIntent(intent: Intent) {
            when (intent) {
                is Intent.ChangeSearchQuery -> {
                    dispatch(Msg.ChangeSearchQuery(intent.query))
                }

                Intent.ClickBack -> {
                    publish(Label.ClickBack)
                }

                is Intent.SearchCharacter -> {
                    scope.launch {
                        try {
                            val character =
                                getCharacterByNameUseCase.getCharacterByName(state().searchQuery)
                                    .toCharacter()
                            publish(Label.SearchCharacter(character))
                        } catch (e: Exception) {
                            dispatch(Msg.SearchNotFound)
                        }
                    }
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when (msg) {
        }
    }
}
