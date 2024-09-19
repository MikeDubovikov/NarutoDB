package com.mdubovikov.narutodb.presentation.items_of_category

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.mdubovikov.narutodb.domain.entity.Category
import com.mdubovikov.narutodb.domain.entity.ItemOfCategory
import com.mdubovikov.narutodb.presentation.extension.componentScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DefaultItemsOfCategoryComponent @AssistedInject constructor(
    private val storeFactory: ItemsOfCategoryStoreFactory,
    @Assisted("category") private val category: Category,
    @Assisted("onItemOfCategoryClick") private val onItemOfCategoryClick: (ItemOfCategory) -> Unit,
    @Assisted("onBackClicked") private val onBackClicked: () -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : ItemsOfCategoryComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create(category) }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    ItemsOfCategoryStore.Label.ClickBack -> {
                        onBackClicked
                    }

                    is ItemsOfCategoryStore.Label.ItemOfCategoryClick -> {
                        onItemOfCategoryClick(it.itemOfCategory)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<ItemsOfCategoryStore.State> = store.stateFlow

    override fun onItemClick(itemOfCategory: ItemOfCategory) {
        store.accept(ItemsOfCategoryStore.Intent.ItemOfCategoryClick(itemOfCategory))
    }

    override fun onClickBack() {
        store.accept(ItemsOfCategoryStore.Intent.ClickBack)
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("category") category: Category,
            @Assisted("onItemOfCategoryClick") onItemOfCategoryClick: (ItemOfCategory) -> Unit,
            @Assisted("onBackClicked") onBackClicked: () -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultItemsOfCategoryComponent
    }
}