package com.mdubovikov.narutodb.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mdubovikov.narutodb.R
import com.mdubovikov.narutodb.domain.entity.CharacterDetails

@Composable
fun Family(
    details: CharacterDetails
) {
    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = stringResource(R.string.family),
        fontSize = 24.sp
    )

    Column(
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clip(MaterialTheme.shapes.large)
    ) {
        BlockInfoFamily(
            textInfo = stringResource(R.string.mother),
            detailsInfo = if (!details.mother.isNullOrEmpty()) {
                details.mother
            } else {
                stringResource(R.string.empty)
            }
        )

        BlockInfoFamily(
            textInfo = stringResource(R.string.father),
            detailsInfo = if (!details.father.isNullOrEmpty()) {
                details.father
            } else {
                stringResource(R.string.empty)
            }
        )

        BlockInfoFamily(
            textInfo = stringResource(R.string.sister),
            detailsInfo = if (!details.sister.isNullOrEmpty()) {
                details.sister
            } else {
                stringResource(R.string.empty)
            }
        )

        BlockInfoFamily(
            textInfo = stringResource(R.string.brother),
            detailsInfo = if (!details.brother.isNullOrEmpty()) {
                details.brother
            } else {
                stringResource(R.string.empty)
            }
        )

        BlockInfoFamily(
            textInfo = stringResource(R.string.daughter),
            detailsInfo = if (!details.daughter.isNullOrEmpty()) {
                details.daughter
            } else {
                stringResource(R.string.empty)
            }
        )

        BlockInfoFamily(
            textInfo = stringResource(R.string.son),
            detailsInfo = if (!details.son.isNullOrEmpty()) {
                details.son
            } else {
                stringResource(R.string.empty)
            }
        )

        BlockInfoFamily(
            textInfo = stringResource(R.string.grandmother),
            detailsInfo = if (!details.grandmother.isNullOrEmpty()) {
                details.grandmother
            } else {
                stringResource(R.string.empty)
            }
        )

        BlockInfoFamily(
            textInfo = stringResource(R.string.grandfather),
            detailsInfo = if (!details.grandfather.isNullOrEmpty()) {
                details.grandfather
            } else {
                stringResource(R.string.empty)
            }
        )
    }
}

@Composable
private fun BlockInfoFamily(
    textInfo: String,
    detailsInfo: String
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.outline.copy(.2f))
            .padding(horizontal = 12.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = textInfo
            )
        }

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(30.dp)
                .background(color = MaterialTheme.colorScheme.outline)
        )

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = detailsInfo
            )
        }
    }
}