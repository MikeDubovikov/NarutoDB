package com.mdubovikov.narutodb.presentation.bookmarks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mdubovikov.narutodb.R
import com.mdubovikov.narutodb.domain.entity.Character
import com.mdubovikov.narutodb.presentation.common.ErrorState
import com.mdubovikov.narutodb.presentation.common.GlowingCard

@Composable
fun BookmarksContent(component: BookmarksComponent) {

    val state by component.model.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopBar(
                categoryName = state.category.name,
                onBackClick = { component.onClickBack() },
            )
        }
    ) { paddings ->
        when (val bookmarks = state.bookmarkState) {
            BookmarksStore.State.BookmarkState.Initial -> {}

            BookmarksStore.State.BookmarkState.Empty -> {
                ErrorState(stringResource(R.string.bookmarks_is_empty))
            }

            is BookmarksStore.State.BookmarkState.Loaded -> {
                BookmarksList(paddings, bookmarks.bookmarkCharacters, component)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    categoryName: String,
    onBackClick: () -> Unit
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
        }
    )
}

@Composable
private fun BookmarksList(
    paddings: PaddingValues,
    bookmarks: List<Character>,
    component: BookmarksComponent
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = paddings,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item(span = { GridItemSpan(2) }) { Spacer(modifier = Modifier.height(8.dp)) }
        items(
            items = bookmarks,
            key = { it.id }
        ) {
            GlowingCard(
                character = it,
                onClick = { component.onBookmarkClick(it) }
            )
        }
        item(span = { GridItemSpan(2) }) { Spacer(modifier = Modifier.height(8.dp)) }
    }
}