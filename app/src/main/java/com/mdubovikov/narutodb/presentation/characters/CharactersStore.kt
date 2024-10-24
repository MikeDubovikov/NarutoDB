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
import com.mdubovikov.narutodb.domain.usecase.GetAllCharactersUseCase
import com.mdubovikov.narutodb.domain.usecase.GetCharacterByNameUseCase
import com.mdubovikov.narutodb.presentation.characters.CharactersStore.Intent
import com.mdubovikov.narutodb.presentation.characters.CharactersStore.Label
import com.mdubovikov.narutodb.presentation.characters.CharactersStore.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface CharactersStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data object ClickBack : Intent
        data class CharacterClick(val character: Character) : Intent
        data class ChangeCharacterOptions(val option: CharacterOptions) : Intent
        data object SearchCharacter : Intent
        data class ChangeSearchQuery(val query: String) : Intent
    }

    data class State(
        val category: Category,
        val selectedCharacterOption: CharacterOptions,
        val charactersState: CharactersState,
        val searchQuery: String,
        val recentQueries: List<String>,
        val isNotFound: Boolean
    ) {
        sealed interface CharactersState {
            data object Initial : CharactersState
            data object Loading : CharactersState
            data object Error : CharactersState
            data class Loaded(val charactersList: Flow<PagingData<Character>>) : CharactersState
        }
    }

    sealed interface Label {
        data object ClickBack : Label
        data class CharacterClick(val character: Character) : Label
        data class SearchCharacter(val character: Character) : Label
    }
}

class CharactersStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getCharacterByNameUseCase: GetCharacterByNameUseCase
) {

    fun create(category: Category): CharactersStore =
        object : CharactersStore, Store<Intent, State, Label> by storeFactory.create(
            name = "CharactersStore",
            initialState = State(
                category = category,
                selectedCharacterOption = CharacterOptions.AllCharacters,
                charactersState = State.CharactersState.Initial,
                searchQuery = "",
                recentQueries = emptyList(),
                isNotFound = false
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data object CharactersIsLoading : Action
        data object CharactersIsError : Action
        data class CharactersLoaded(val characterList: Flow<PagingData<Character>>) : Action
    }

    private sealed interface Msg {
        data object CharactersIsLoading : Msg
        data object CharactersIsError : Msg
        data class CharactersLoaded(val characterList: Flow<PagingData<Character>>) : Msg
        data class ChangeSearchQuery(val query: String) : Msg
        data object SearchNotFound : Msg
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                dispatch(Action.CharactersIsLoading)
                try {
                    dispatch(
                        Action.CharactersLoaded(getAllCharactersUseCase.getAllCharacters())
                    )
                } catch (e: Exception) {
                    dispatch(Action.CharactersIsError)
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
                        dispatch(Msg.CharactersIsLoading)
                        try {
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
                        } catch (e: Exception) {
                            dispatch(Msg.CharactersIsError)
                        }
                    }
                }

                is Intent.ChangeSearchQuery -> {
                    dispatch(Msg.ChangeSearchQuery(intent.query))
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
                Action.CharactersIsLoading -> {
                    dispatch(Msg.CharactersIsLoading)
                }

                Action.CharactersIsError -> {
                    dispatch(Msg.CharactersIsError)
                }

                is Action.CharactersLoaded -> {
                    dispatch(Msg.CharactersLoaded(action.characterList))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when (msg) {
            Msg.CharactersIsLoading -> copy(charactersState = State.CharactersState.Loading)
            Msg.CharactersIsError -> copy(charactersState = State.CharactersState.Error)
            is Msg.CharactersLoaded -> copy(charactersState = State.CharactersState.Loaded(msg.characterList))
            is Msg.ChangeSearchQuery -> copy(searchQuery = msg.query)
            Msg.SearchNotFound -> copy(isNotFound = true)
        }
    }
}