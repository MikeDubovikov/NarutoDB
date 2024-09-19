package com.mdubovikov.narutodb.presentation.details

import kotlinx.coroutines.flow.StateFlow

interface DetailsComponent {

    val model: StateFlow<DetailsStore.State>

    fun onClickChangeBookmarkStatus()

    fun onClickBack()
}