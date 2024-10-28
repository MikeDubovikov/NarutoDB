package com.mdubovikov.narutodb.presentation.details

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.domain.entity.CharacterDetails
import com.mdubovikov.narutodb.domain.usecase.ChangeBookmarkStatusUseCase
import com.mdubovikov.narutodb.domain.usecase.GetCharacterByIdUseCase
import com.mdubovikov.narutodb.domain.usecase.ObserveBookmarkStateUseCase
import com.mdubovikov.narutodb.presentation.details.DetailsStore.Intent
import com.mdubovikov.narutodb.presentation.details.DetailsStore.Label
import com.mdubovikov.narutodb.presentation.details.DetailsStore.State
import kotlinx.coroutines.launch
import javax.inject.Inject

interface DetailsStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data object ClickBack : Intent
        data object ClickChangeBookmarkStatus : Intent
    }

    data class State(
        val character: Character,
        val isBookmarked: Boolean,
        val detailsState: DetailsState
    ) {
        sealed interface DetailsState {
            data object Initial : DetailsState
            data object Loading : DetailsState
            data object Error : DetailsState
            data class Loaded(val itemDetails: CharacterDetails) : DetailsState
        }
    }

    sealed interface Label {
        data object ClickBack : Label
    }
}

class DetailsStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val changeBookmarkStatusUseCase: ChangeBookmarkStatusUseCase,
    private val observeBookmarkStateUseCase: ObserveBookmarkStateUseCase
) {

    fun create(character: Character): DetailsStore =
        object : DetailsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "DetailsStore",
            initialState = State(
                character = character,
                isBookmarked = false,
                detailsState = State.DetailsState.Initial
            ),
            bootstrapper = BootstrapperImpl(character),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data object DetailsLoading : Action
        data object DetailsError : Action
        data class DetailsLoaded(val itemDetails: CharacterDetails) : Action
        data class BookmarkStatusChanged(val isBookmarked: Boolean) : Action
    }

    private sealed interface Msg {
        data object DetailsLoading : Msg
        data object DetailsError : Msg
        data class DetailsLoaded(val itemDetails: CharacterDetails) : Msg
        data class BookmarkStatusChanged(val isBookmarked: Boolean) : Msg
    }

    private inner class BootstrapperImpl(
        private val character: Character
    ) : CoroutineBootstrapper<Action>() {
        override fun invoke() {

            scope.launch {
                dispatch(Action.DetailsLoading)
                try {
                    dispatch(
                        Action.DetailsLoaded(getCharacterByIdUseCase.getCharacterById(character.id))
                    )
                } catch (e: Exception) {
                    dispatch(Action.DetailsError)
                }
            }

            scope.launch {
                observeBookmarkStateUseCase(character.id).collect {
                    dispatch(Action.BookmarkStatusChanged(it))
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

                Intent.ClickChangeBookmarkStatus -> {
                    scope.launch {
                        if (state().isBookmarked) {
                            changeBookmarkStatusUseCase.removeFromBookmarks(state().character.id)
                        } else {
                            changeBookmarkStatusUseCase.addToBookmarks(state().character)
                        }
                    }
                }
            }
        }

        override fun executeAction(action: Action) {
            when (action) {

                Action.DetailsLoading -> {
                    dispatch(Msg.DetailsLoading)
                }

                Action.DetailsError -> {
                    dispatch(Msg.DetailsError)
                }

                is Action.DetailsLoaded -> {
                    dispatch(Msg.DetailsLoaded(action.itemDetails))
                }

                is Action.BookmarkStatusChanged -> {
                    dispatch(Msg.BookmarkStatusChanged(action.isBookmarked))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when (msg) {

            Msg.DetailsLoading -> {
                copy(detailsState = State.DetailsState.Loading)
            }

            Msg.DetailsError -> {
                copy(detailsState = State.DetailsState.Error)
            }

            is Msg.DetailsLoaded -> {
                copy(detailsState = State.DetailsState.Loaded(msg.itemDetails))
            }

            is Msg.BookmarkStatusChanged -> {
                copy(isBookmarked = msg.isBookmarked)
            }
        }
    }
}