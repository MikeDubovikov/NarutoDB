package com.mdubovikov.narutodb.presentation.details

import android.graphics.Paint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.mdubovikov.narutodb.R
import com.mdubovikov.narutodb.domain.entity.CharacterDetails
import com.mdubovikov.narutodb.presentation.common.ErrorImageState
import com.mdubovikov.narutodb.presentation.common.ErrorState
import com.mdubovikov.narutodb.presentation.common.LoadingState

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
                DetailsStore.State.DetailsState.Initial -> {}

                DetailsStore.State.DetailsState.Error -> {
                    ErrorState(stringResource(R.string.something_went_wrong))
                }

                DetailsStore.State.DetailsState.Loading -> {
                    LoadingState()
                }

                is DetailsStore.State.DetailsState.Loaded -> {
                    DetailsInfo(details = detailsState.itemDetails)
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
private fun DetailsInfo(details: CharacterDetails) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item("image") {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .widthIn(max = 500.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.2f)
                    .fillMaxHeight()
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(40.dp)),
                model = details.image,
                contentScale = ContentScale.Crop,
                error = { ErrorImageState() },
                contentDescription = stringResource(R.string.character_image)
            )
        }

        item("detailCard") {
            DetailsGlowingCard(details)
        }
    }
}

@Composable
private fun DetailsGlowingCard(
    details: CharacterDetails,
    modifier: Modifier = Modifier.padding(
        top = 24.dp,
        start = 8.dp,
        end = 8.dp,
        bottom = 8.dp
    ),
    glowingColor: Color = MaterialTheme.colorScheme.primary,
    containerColor: Color = MaterialTheme.colorScheme.background,
    cornersRadius: Dp = 20.dp,
    glowingRadius: Dp = 10.dp,
    xShifting: Dp = 0.dp,
    yShifting: Dp = 0.dp
) {
    Box(
        modifier = modifier
            .drawBehind {
                val canvasSize = size
                drawContext.canvas.nativeCanvas.apply {
                    drawRoundRect(
                        0f,
                        0f,
                        canvasSize.width,
                        canvasSize.height,
                        cornersRadius.toPx(),
                        cornersRadius.toPx(),
                        Paint().apply {
                            color = containerColor.toArgb()
                            isAntiAlias = true
                            setShadowLayer(
                                glowingRadius.toPx(),
                                xShifting.toPx(), yShifting.toPx(),
                                glowingColor.copy(alpha = 0.85f).toArgb()
                            )
                        }
                    )
                }
            }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = details.name,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            CharacterPersonal(
                modifier = Modifier.padding(8.dp),
                details = details
            )

            Family(
                modifier = Modifier.padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                ),
                details = details
            )

            Jutsu(
                modifier = Modifier.padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                ),
                isAvailableList = details.jutsu?.size != null,
                details = details
            )
        }
    }
}