package com.mdubovikov.narutodb.presentation.characters

import androidx.paging.PagingData
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.mdubovikov.narutodb.data.mapper.toCharacter
import com.mdubovikov.narutodb.domain.entity.Category
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.domain.usecase.DeleteQueryUseCase
import com.mdubovikov.narutodb.domain.usecase.GetAllCharactersUseCase
import com.mdubovikov.narutodb.domain.usecase.GetCharacterByNameUseCase
import com.mdubovikov.narutodb.domain.usecase.GetRecentQueriesUseCase
import com.mdubovikov.narutodb.domain.usecase.SaveQueryUseCase
import com.mdubovikov.narutodb.presentation.characters.CharactersStore.Intent
import com.mdubovikov.narutodb.presentation.characters.CharactersStore.Label
import com.mdubovikov.narutodb.presentation.characters.CharactersStore.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface CharactersStore : Store<Intent, State, Label> {
    sealed interface Intent {
        data object ClickBack : Intent
        data class CharacterClick(val character: Character) : Intent
        data class ChangeCharacterOptions(val option: CharacterOptions) : Intent
        data object SearchCharacter : Intent
        data class ChangeSearchQuery(val query: String) : Intent
        data class SaveSearchQuery(val query: String) : Intent
        data class DeleteSearchQuery(val query: String) : Intent
    }

    data class State(
        val category: Category,
        val charactersList: Flow<PagingData<Character>>,
        val selectedCharacterOption: CharacterOptions,
        val searchQuery: String,
        val recentQueries: List<String>,
        val isNotFound: Boolean
    )

    sealed interface Label {
        data object ClickBack : Label
        data class CharacterClick(val character: Character) : Label
        data class SearchCharacter(val character: Character) : Label
    }
}

class CharactersStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getCharacterByNameUseCase: GetCharacterByNameUseCase,
    private val getRecentQueriesUseCase: GetRecentQueriesUseCase,
    private val saveQueryUseCase: SaveQueryUseCase,
    private val deleteQueryUseCase: DeleteQueryUseCase
) {
    fun create(category: Category): CharactersStore =
        object : CharactersStore, Store<Intent, State, Label> by storeFactory.create(
            name = "CharactersStore",
            initialState = State(
                category = category,
                charactersList = emptyFlow(),
                selectedCharacterOption = CharacterOptions.AllCharacters,
                searchQuery = "",
                recentQueries = emptyList(),
                isNotFound = false
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data class CharactersLoaded(val characterList: Flow<PagingData<Character>>) : Action
        data class RecentQueriesLoaded(val queriesList: List<String>) : Action
    }

    private sealed interface Msg {
        data class CharactersLoaded(val characterList: Flow<PagingData<Character>>) : Msg
        data class RecentQueriesLoaded(val queriesList: List<String>) : Msg
        data class ChangeSearchQuery(val query: String) : Msg
        data object SearchNotFound : Msg
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                dispatch(Action.CharactersLoaded(getAllCharactersUseCase.getAllCharacters()))
            }

            scope.launch {
                getRecentQueriesUseCase.invoke().collect {
                    dispatch(Action.RecentQueriesLoaded(it))
                }
            }
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent) {
            when (intent) {
                Intent.ClickBack -> {
                    publish(Label.ClickBack)
                }

                is Intent.CharacterClick -> {
                    publish(Label.CharacterClick(intent.character))
                }

                is Intent.ChangeCharacterOptions -> {
                    scope.launch {
                        when (intent.option) {
                            CharacterOptions.AllCharacters -> {
                                dispatch(Msg.CharactersLoaded(getAllCharactersUseCase.getAllCharacters()))
                            }

                            CharacterOptions.AllAkatsuki -> {
                                dispatch(Msg.CharactersLoaded(getAllCharactersUseCase.getAllAkatsuki()))
                            }

                            CharacterOptions.AllTailedBeasts -> {
                                dispatch(Msg.CharactersLoaded(getAllCharactersUseCase.getAllTailedBeasts()))
                            }

                            CharacterOptions.AllKara -> {
                                dispatch(Msg.CharactersLoaded(getAllCharactersUseCase.getAllKara()))
                            }
                        }
                    }
                }

                is Intent.ChangeSearchQuery -> {
                    dispatch(Msg.ChangeSearchQuery(intent.query))
                }

                is Intent.SaveSearchQuery -> {
                    scope.launch {
                        saveQueryUseCase.invoke(intent.query)
                    }
                }

                is Intent.DeleteSearchQuery -> {
                    scope.launch {
                        deleteQueryUseCase.invoke(intent.query)
                    }
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

        override fun executeAction(action: Action) {
            when (action) {
                is Action.RecentQueriesLoaded -> {
                    dispatch(Msg.RecentQueriesLoaded(action.queriesList))
                }

                is Action.CharactersLoaded -> {
                    dispatch(Msg.CharactersLoaded(action.characterList))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when (msg) {
            is Msg.CharactersLoaded -> {
                copy(charactersList = msg.characterList)
            }

            is Msg.RecentQueriesLoaded -> {
                copy(recentQueries = msg.queriesList)
            }

            is Msg.ChangeSearchQuery -> {
                copy(
                    searchQuery = msg.query,
                    isNotFound = false
                )
            }

            Msg.SearchNotFound -> {
                copy(isNotFound = true)
            }
        }
    }
}