package com.mdubovikov.narutodb.presentation.bookmarks

import android.graphics.Paint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
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
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mdubovikov.narutodb.R
import com.mdubovikov.narutodb.domain.entity.Character

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

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            contentPadding = paddings,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item(span = { GridItemSpan(2) }) { Spacer(modifier = Modifier.height(8.dp)) }
            items(
                items = state.bookmarkCharacters,
                key = { it.bookmarkCharacter.id }
            ) {
                GlowingCard(
                    character = it.bookmarkCharacter,
                    onClick = { component.onBookmarkClick(it.bookmarkCharacter) }
                )
            }
            item(span = { GridItemSpan(2) }) { Spacer(modifier = Modifier.height(8.dp)) }
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
fun GlowingCard(
    character: Character,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.padding(start = 16.dp, end = 16.dp),
    glowingColor: Color = MaterialTheme.colorScheme.primary,
    containerColor: Color = MaterialTheme.colorScheme.background,
    cornersRadius: Dp = 40.dp,
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
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick() }
        ) {
            Box(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(176.dp)
                        .padding(1.dp)
                        .clip(
                            shape = RoundedCornerShape(40.dp).copy(
                                bottomStart = CornerSize(0),
                                bottomEnd = CornerSize(0)
                            )
                        ),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.image)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Crop,
                    filterQuality = FilterQuality.None,
                    contentDescription = stringResource(R.string.character_image)
                )
            }
            Box(
                modifier = Modifier
                    .size(width = 180.dp, height = 80.dp)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = character.name,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp)
                )
            }
        }
    }
}