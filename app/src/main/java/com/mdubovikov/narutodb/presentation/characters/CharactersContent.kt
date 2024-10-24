package com.mdubovikov.narutodb.presentation.characters

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.mdubovikov.narutodb.R
import com.mdubovikov.narutodb.presentation.common.ErrorState
import com.mdubovikov.narutodb.presentation.common.GlowingCard
import com.mdubovikov.narutodb.presentation.common.LoadingState

@Composable
fun CharactersContent(component: CharactersComponent) {

    val state by component.model.collectAsState()
    var searchIsEnabled by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopBar(
                categoryName = state.category.name,
                onBackClick = { component.onClickBack() },
                onSearchClick = { searchIsEnabled = !searchIsEnabled }
            )
        }
    ) { padding ->
        if (searchIsEnabled) {
            SearchCharacterBar(
                modifier = Modifier.padding(top = 80.dp),
                state = state,
                component = component
            )
        } else {
            Column(
                modifier = Modifier.padding(padding)
            ) {
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

        if (state.isNotFound) {
            NotFound()
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
                    contentDescription = stringResource(R.string.search),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCharacterBar(
    modifier: Modifier = Modifier,
    state: CharactersStore.State,
    component: CharactersComponent
) {
    var expanded by rememberSaveable { mutableStateOf(true) }
    val listItems = remember {
        mutableListOf(
            "Aho Bird",
            "Naruto Uzumaki"
        )
    }
    Box(
        Modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }) {
        SearchBar(
            modifier = modifier,
            inputField = {
                SearchBarDefaults.InputField(
                    query = state.searchQuery,
                    onQueryChange = { component.changeSearchQuery(it) },
                    onSearch = {
                        component.searchCharacter()
                        listItems.add(it)
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text(text = stringResource(R.string.search)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search_icon)
                        )
                    },
                    trailingIcon = {
                        if (expanded) {
                            Icon(
                                modifier = Modifier.clickable {
                                    if (state.searchQuery.isNotEmpty()) {
                                        component.changeSearchQuery("")
                                    }
                                },
                                imageVector = Icons.Default.Close,
                                contentDescription = stringResource(R.string.close_icon)
                            )
                        }
                    }
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            listItems.forEach { recentItem ->
                Row(modifier = Modifier.padding(all = 14.dp)) {
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        imageVector = Icons.Default.Refresh,
                        contentDescription = stringResource(R.string.recent_icon)
                    )

                    Text(
                        modifier = Modifier.clickable {
                            component.changeSearchQuery(recentItem)
                            component.searchCharacter()
                        },
                        text = recentItem
                    )
                }
            }
        }
    }
}

@Composable
private fun NotFound() {
    Toast.makeText(
        LocalContext.current,
        stringResource(R.string.character_not_found),
        Toast.LENGTH_LONG
    ).show()
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