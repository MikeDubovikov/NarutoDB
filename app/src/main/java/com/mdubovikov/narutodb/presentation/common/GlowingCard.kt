package com.mdubovikov.narutodb.presentation.common

import android.graphics.Paint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.mdubovikov.narutodb.R
import com.mdubovikov.narutodb.domain.entity.Character

@Composable
fun GlowingCard(
    character: Character,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.padding(start = 8.dp, end = 8.dp),
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
                .clip(shape = RoundedCornerShape(40.dp))
                .clickable { onClick() }
        ) {
            Box(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .size(192.dp)
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
                    loading = { Loading() },
                    error = { ErrorImageState() },
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

@Composable
private fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .shimmerEffect()
    )
}