package com.mdubovikov.narutodb.presentation.categories

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.mdubovikov.narutodb.domain.entity.Category
import com.mdubovikov.narutodb.presentation.extension.componentScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DefaultCategoriesComponent @AssistedInject constructor(
    private val storeFactory: CategoriesStoreFactory,
    @Assisted("onCategoryClick") private val onCategoryClick: (Category) -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : CategoriesComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    is CategoriesStore.Label.CategoryClick -> {
                        onCategoryClick(it.category)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<CategoriesStore.State> = store.stateFlow

    override fun onCategoryClicked(category: Category) {
        store.accept(CategoriesStore.Intent.CategoryClick(category))
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("onCategoryClick") onCategoryClick: (Category) -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultCategoriesComponent
    }
}