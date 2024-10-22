package com.mdubovikov.narutodb.presentation.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.mdubovikov.narutodb.R
import com.mdubovikov.narutodb.presentation.common.ErrorState
import com.mdubovikov.narutodb.presentation.common.GlowingCard
import com.mdubovikov.narutodb.presentation.common.LoadingState

@Composable
fun CharactersContent(component: CharactersComponent) {

    val state by component.model.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopBar(
                categoryName = state.category.name,
                onBackClick = { component.onClickBack() },
                onSearchClick = { component.onClickSearch() }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding)
        ) {
            Column {
                SegmentedButtons(component)
                when (val characterState = state.charactersState) {
                    CharactersStore.State.CharactersState.Initial -> {}

                    CharactersStore.State.CharactersState.Error -> {
                        ErrorState(stringResource(R.string.something_went_wrong))
                    }

                    CharactersStore.State.CharactersState.Loading -> {
                        LoadingState()
                    }

                    is CharactersStore.State.CharactersState.Loaded -> {
                        CharactersList(characterState, component)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    categoryName: String,
    onBackClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(text = categoryName) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.go_back),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            IconButton(onClick = { onSearchClick() }) {
                val icon = Icons.Filled.Search
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

@Composable
private fun SegmentedButtons(component: CharactersComponent) {

    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    val options = listOf("All", "Akatsuki", "Beasts", "Kara")

    SingleChoiceSegmentedButtonRow(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
    ) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                onClick = {
                    when (index) {
                        0 -> component.changeCharacterOption(CharacterOptions.AllCharacters)
                        1 -> component.changeCharacterOption(CharacterOptions.AllAkatsuki)
                        2 -> component.changeCharacterOption(CharacterOptions.AllTailedBeasts)
                        3 -> component.changeCharacterOption(CharacterOptions.AllKara)
                    }
                    selectedIndex = index
                },
                selected = index == selectedIndex
            ) {
                Text(label)
            }
        }
    }
}

@Composable
private fun CharactersList(
    state: CharactersStore.State.CharactersState.Loaded,
    component: CharactersComponent
) {
    val charactersList = state.charactersList.collectAsLazyPagingItems()
    val scrollableListState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = scrollableListState,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item(span = { GridItemSpan(2) }) { Spacer(modifier = Modifier.height(8.dp)) }
        items(charactersList.itemCount) { item ->
            charactersList[item]?.let {
                GlowingCard(
                    character = it,
                    onClick = { component.onCharacterClick(it) }
                )
            }
        }
        item(span = { GridItemSpan(2) }) { Spacer(modifier = Modifier.height(8.dp)) }
    }
}