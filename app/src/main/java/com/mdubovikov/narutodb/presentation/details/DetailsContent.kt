package com.mdubovikov.narutodb.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mdubovikov.narutodb.R
import com.mdubovikov.narutodb.domain.entity.CharacterDetails

@Composable
fun DetailsContent(component: DetailsComponent) {

    val state by component.model.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            TopBar(
                cityName = state.character.name,
                isCityFavourite = state.isBookmarked,
                onBackClick = { component.onClickBack() },
                onClickChangeFavouriteStatus = { component.onClickChangeBookmarkStatus() }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (val detailsState = state.detailsState) {
                DetailsStore.State.DetailsState.Error -> {
                    Error()
                }

                DetailsStore.State.DetailsState.Initial -> {
                }

                is DetailsStore.State.DetailsState.Loaded -> {
                    DetailsInfo(details = detailsState.itemDetails)
                }

                DetailsStore.State.DetailsState.Loading -> {
                    Loading()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    cityName: String,
    isCityFavourite: Boolean,
    onBackClick: () -> Unit,
    onClickChangeFavouriteStatus: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = { Text(text = cityName) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            IconButton(onClick = { onClickChangeFavouriteStatus() }) {
                val icon = if (isCityFavourite) {
                    ImageVector.vectorResource(R.drawable.bookmark_filled)
                } else {
                    ImageVector.vectorResource(R.drawable.bookmark_outline)
                }
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
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun Error() {
}

@Composable
private fun DetailsInfo(details: CharacterDetails) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item("image") {
            AsyncImage(
                modifier = Modifier
                    .widthIn(max = 500.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.2f)
                    .fillMaxHeight(),
                model = details.image,
                contentDescription = stringResource(R.string.character_image)
            )
        }

        item("name") {
            Text(
                text = details.name,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            )
        }

        item("personal") {
            CharacterPersonal(details)
        }

        item("family") {
            Family(details)
        }

        item("jutsu") {
            val isAvailable = details.jutsu?.size == null
            Jutsu(isAvailable, details)
        }
    }
}