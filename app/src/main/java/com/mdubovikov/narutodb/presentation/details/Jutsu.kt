package com.mdubovikov.narutodb.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mdubovikov.narutodb.R
import com.mdubovikov.narutodb.domain.entity.CharacterDetails

@Composable
fun Jutsu(
    modifier: Modifier = Modifier,
    isAvailableList: Boolean,
    details: CharacterDetails
) {
    Text(
        modifier = modifier.padding(top = 16.dp),
        text = stringResource(R.string.jutsu),
        fontSize = 24.sp
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.outline.copy(.2f))
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        if (isAvailableList) {
            repeat(
                details.jutsu?.size ?: 0,
                action = {
                    Text(
                        text = "- " + (details.jutsu?.get(it) ?: 0).toString(),
                        fontSize = 14.sp
                    )
                }
            )
        } else {
            Text(
                text = stringResource(R.string.empty),
                fontSize = 14.sp
            )
        }
    }
}