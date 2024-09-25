package com.mdubovikov.narutodb.presentation.characters

import com.mdubovikov.narutodb.domain.entity.Character
import kotlinx.coroutines.flow.StateFlow

interface CharactersComponent {

    val model: StateFlow<CharactersStore.State>

    fun onCharacterClick(character: Character)

    fun onClickBack()
}