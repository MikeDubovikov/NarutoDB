package com.mdubovikov.narutodb.presentation.characters

import com.mdubovikov.narutodb.domain.entity.Character
import kotlinx.coroutines.flow.StateFlow

interface CharactersComponent {
    val model: StateFlow<CharactersStore.State>
    fun changeCharacterOption(option: CharacterOptions)
    fun onCharacterClick(character: Character)
    fun onClickBack()
    fun changeSearchQuery(query: String)
    fun saveQuery(query: String)
    fun deleteQuery(query: String)
    fun searchCharacter()
}