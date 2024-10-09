package com.mdubovikov.narutodb.presentation.bookmarks

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.mdubovikov.narutodb.domain.entity.Category
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.domain.usecase.GetBookmarksUseCase
import com.mdubovikov.narutodb.presentation.bookmarks.BookmarksStore.Intent
import com.mdubovikov.narutodb.presentation.bookmarks.BookmarksStore.Label
import com.mdubovikov.narutodb.presentation.bookmarks.BookmarksStore.State
import kotlinx.coroutines.launch
import javax.inject.Inject

interface BookmarksStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data object ClickBack : Intent

        data class BookmarkClick(val character: Character) : Intent
    }

    data class State(
        val category: Category,
        val bookmarkCharacters: List<BookmarkCharacter>
    ) {
        data class BookmarkCharacter(
            val bookmarkCharacter: Character
        )
    }

    sealed interface Label {

        data object ClickBack : Label

        data class BookmarkClick(val character: Character) : Label
    }
}

class BookmarksStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getBookmarksUseCase: GetBookmarksUseCase
) {

    fun create(category: Category): BookmarksStore =
        object : BookmarksStore, Store<Intent, State, Label> by storeFactory.create(
            name = "BookmarksStore",
            initialState = State(
                category = category,
                bookmarkCharacters = listOf()
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {

        data class BookmarksLoaded(val bookmarks: List<Character>) : Action
    }

    private sealed interface Msg {

        data class BookmarksLoaded(val bookmarks: List<Character>) : Msg
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                getBookmarksUseCase().collect {
                    dispatch(Action.BookmarksLoaded(bookmarks = it))
                }
            }
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent) {
            when (intent) {

                is Intent.BookmarkClick -> {
                    publish(Label.BookmarkClick(intent.character))
                }

                Intent.ClickBack -> {
                    publish(Label.ClickBack)
                }
            }
        }

        override fun executeAction(action: Action) {
            when (action) {

                is Action.BookmarksLoaded -> {
                    dispatch(Msg.BookmarksLoaded(action.bookmarks))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when (msg) {

            is Msg.BookmarksLoaded -> {
                copy(bookmarkCharacters = msg.bookmarks.map {
                    State.BookmarkCharacter(it)
                })
            }
        }
    }
}
