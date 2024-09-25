package com.mdubovikov.narutodb.presentation.bookmarks

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

class DefaultBookmarksComponent @AssistedInject constructor(
    private val storeFactory: BookmarksStoreFactory,
    @Assisted("onBackClicked") private val onBackClicked: () -> Unit,
    @Assisted("onBookmarkClicked") private val onBookmarkClicked: (Character) -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : BookmarksComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {

                    is BookmarksStore.Label.BookmarkClick -> {
                        onBookmarkClicked(it.character)
                    }

                    BookmarksStore.Label.ClickBack -> {
                        onBackClicked()
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<BookmarksStore.State> = store.stateFlow

    override fun onBookmarkClick(character: Character) {
        store.accept(BookmarksStore.Intent.BookmarkClick(character))
    }

    override fun onClickBack() {
        store.accept(BookmarksStore.Intent.ClickBack)
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("onBackClicked") onBackClicked: () -> Unit,
            @Assisted("onBookmarkClicked") onBookmarkClicked: (Character) -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultBookmarksComponent
    }
}