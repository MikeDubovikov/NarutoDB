package com.mdubovikov.narutodb.presentation.search

import kotlinx.coroutines.flow.StateFlow

interface SearchComponent {

    val model: StateFlow<SearchStore.State>

    fun changeSearchQuery(query: String)

    fun searchCharacter()

    fun onClickBack()
}