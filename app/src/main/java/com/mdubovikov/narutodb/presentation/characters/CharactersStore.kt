package com.mdubovikov.narutodb.presentation.characters

import androidx.paging.PagingData
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.mdubovikov.narutodb.domain.entity.Category
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.domain.usecase.GetAllCharactersUseCase
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
        data object ClickSearch : Intent
        data class CharacterClick(val character: Character) : Intent
        data class ChangeCharacterOptions(val option: CharacterOptions) : Intent
    }

    data class State(
        val category: Category,
        val selectedCharacterOption: CharacterOptions,
        val charactersList: Flow<PagingData<Character>>,
        val isLoading: Boolean,
        val isError: Boolean
    )

    sealed interface Label {
        data object ClickBack : Label
        data object ClickSearch : Label
        data class CharacterClick(val character: Character) : Label
    }
}

class CharactersStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) {

    fun create(category: Category): CharactersStore =
        object : CharactersStore, Store<Intent, State, Label> by storeFactory.create(
            name = "CharactersStore",
            initialState = State(
                category = category,
                selectedCharacterOption = CharacterOptions.AllCharacters,
                charactersList = emptyFlow(),
                isLoading = false,
                isError = false
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

                Intent.ClickSearch -> {
                    publish(Label.ClickSearch)
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
            Msg.CharactersIsLoading -> copy(isLoading = true)
            Msg.CharactersIsError -> copy(isError = true)
            is Msg.CharactersLoaded -> copy(charactersList = msg.characterList)
        }
    }
}