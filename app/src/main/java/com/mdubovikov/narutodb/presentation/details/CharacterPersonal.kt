package com.mdubovikov.narutodb.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mdubovikov.narutodb.R
import com.mdubovikov.narutodb.domain.entity.CharacterDetails

@Composable
fun CharacterPersonal(
    details: CharacterDetails
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(MaterialTheme.shapes.large)
    ) {
        BlockInfoPersonal(
            detailsInfoFirst = if (!details.birthdate.isNullOrEmpty()) {
                details.birthdate
            } else {
                stringResource(R.string.empty)
            },
            textInfoFirst = stringResource(R.string.birthdate),
            detailsInfoSecond = if (!details.age.isNullOrEmpty()) {
                details.age
            } else {
                stringResource(R.string.empty)
            },
            textInfoSecond = stringResource(R.string.age)
        )

        BlockInfoPersonal(
            detailsInfoFirst = if (!details.sex.isNullOrEmpty()) {
                details.sex
            } else {
                stringResource(R.string.empty)
            },
            textInfoFirst = stringResource(R.string.sex),
            detailsInfoSecond = if (!details.status.isNullOrEmpty()) {
                details.status
            } else {
                stringResource(R.string.empty)
            },
            textInfoSecond = stringResource(R.string.status)
        )

        BlockInfoPersonal(
            detailsInfoFirst = if (!details.height.isNullOrEmpty()) {
                details.height
            } else {
                stringResource(R.string.empty)
            },
            textInfoFirst = stringResource(R.string.height),
            detailsInfoSecond = if (!details.weight.isNullOrEmpty()) {
                details.weight
            } else {
                stringResource(R.string.empty)
            },
            textInfoSecond = stringResource(R.string.weight)
        )

        BlockInfoPersonal(
            detailsInfoFirst = if (!details.clan?.first().isNullOrEmpty()) {
                details.clan?.first() ?: ""
            } else {
                stringResource(R.string.empty)
            },
            textInfoFirst = stringResource(R.string.clan),
            detailsInfoSecond = if (!details.ninjaRank.isNullOrEmpty()) {
                details.ninjaRank
            } else {
                stringResource(R.string.empty)
            },
            textInfoSecond = stringResource(R.string.rank)
        )
    }
}

@Composable
private fun BlockInfoPersonal(
    detailsInfoFirst: String,
    textInfoFirst: String,
    detailsInfoSecond: String,
    textInfoSecond: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.outline.copy(.2f))
            .padding(horizontal = 12.dp, vertical = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = detailsInfoFirst,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = textInfoFirst,
                color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(30.dp)
                .background(color = MaterialTheme.colorScheme.outline)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Row {
                Text(
                    text = detailsInfoSecond,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(Modifier.height(4.dp))

            Text(
                text = textInfoSecond,
                color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}