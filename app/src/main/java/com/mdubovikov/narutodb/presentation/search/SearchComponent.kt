package com.mdubovikov.narutodb.presentation.search

import com.mdubovikov.narutodb.domain.entity.Category
import kotlinx.coroutines.flow.StateFlow

interface SearchComponent {

    val model: StateFlow<SearchStore.State>

    fun changeSearchQuery(query: String)

    fun searchCharacter()

    fun onClickBack()
}