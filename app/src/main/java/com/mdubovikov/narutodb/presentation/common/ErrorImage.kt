package com.mdubovikov.narutodb.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mdubovikov.narutodb.R

@Composable
fun ErrorImageState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = if (isSystemInDarkTheme()) {
                painterResource(R.drawable.naruto_logo_dark)
            } else {
                painterResource(R.drawable.naruto_logo_light)
            },
            contentDescription = stringResource(R.string.error_loading_image)
        )
    }
}