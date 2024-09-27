package com.mdubovikov.narutodb.presentation.categories

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.mdubovikov.narutodb.domain.entity.Category
import com.mdubovikov.narutodb.domain.usecase.GetCategoriesUseCase
import com.mdubovikov.narutodb.presentation.categories.CategoriesStore.Intent
import com.mdubovikov.narutodb.presentation.categories.CategoriesStore.Label
import com.mdubovikov.narutodb.presentation.categories.CategoriesStore.State
import kotlinx.coroutines.launch
import javax.inject.Inject

interface CategoriesStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data class CharactersClick(val category: Category) : Intent

        data class BookmarksClick(val category: Category) : Intent
    }

    data class State(
        val mainChooseList: List<CategoryItem>
    ) {
        data class CategoryItem(
            val category: Category
        )
    }

    sealed interface Label {

        data class CharactersClick(val category: Category) : Label

        data class BookmarksClick(val category: Category) : Label
    }
}

class CategoriesStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getCategoriesUseCase: GetCategoriesUseCase
) {

    fun create(): CategoriesStore =
        object : CategoriesStore, Store<Intent, State, Label> by storeFactory.create(
            name = "CategoriesStore",
            initialState = State(listOf()),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {

        data class CategoriesLoaded(val categories: List<Category>) : Action
    }

    private sealed interface Msg {

        data class CategoriesLoaded(val categories: List<Category>) : Msg
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                dispatch(Action.CategoriesLoaded(getCategoriesUseCase.invoke()))
            }
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent) {
            when (intent) {

                is Intent.CharactersClick -> {
                    publish(Label.CharactersClick(intent.category))
                }

                is Intent.BookmarksClick -> {
                    publish(Label.BookmarksClick(intent.category))
                }
            }
        }

        override fun executeAction(action: Action) {
            when (action) {

                is Action.CategoriesLoaded -> {
                    dispatch(Msg.CategoriesLoaded(action.categories))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when (msg) {

            is Msg.CategoriesLoaded -> {
                copy(mainChooseList = msg.categories.map {
                    State.CategoryItem(it)
                })
            }
        }
    }
}
