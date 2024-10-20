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
import androidx.compose.foundation.shape.CornerSize
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
    details: CharacterDetails,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .clip(
                    MaterialTheme.shapes.large.copy(
                        bottomStart = CornerSize(0),
                        bottomEnd = CornerSize(0)
                    )
                )
                .background(MaterialTheme.colorScheme.outline.copy(.2f))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                val birthdate =
                    if (details.birthdate.isNullOrEmpty()) stringResource(R.string.empty) else details.birthdate

                Text(
                    text = birthdate,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.birthdate),
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
                    val age =
                        if (details.age.isNullOrEmpty()) stringResource(R.string.empty) else details.age

                    Text(
                        text = age,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.age),
                    color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.outline.copy(.2f))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                val sex =
                    if (details.sex.isNullOrEmpty()) stringResource(R.string.empty) else details.sex

                Text(
                    text = sex,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.sex),
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
                    val status =
                        if (details.status.isNullOrEmpty()) stringResource(R.string.empty) else details.status

                    Text(
                        text = status,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.status),
                    color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.outline.copy(.2f))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                val height =
                    if (details.height.isNullOrEmpty()) stringResource(R.string.empty) else details.height

                Text(
                    text = height,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.height),
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

                    val weight =
                        if (details.weight.isNullOrEmpty()) stringResource(R.string.empty) else details.weight

                    Text(
                        text = weight,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.weight),
                    color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .clip(
                    MaterialTheme.shapes.large.copy(
                        topStart = CornerSize(0),
                        topEnd = CornerSize(0)
                    )
                )
                .background(MaterialTheme.colorScheme.outline.copy(.2f))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                val clan = if (details.clan?.first().isNullOrEmpty()) {
                    stringResource(R.string.empty)
                } else {
                    details.clan?.first() ?: ""
                }

                Text(
                    text = clan,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.clan),
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
                    val rank =
                        if (details.ninjaRank.isNullOrEmpty()) stringResource(R.string.empty) else details.ninjaRank

                    Text(
                        text = rank,
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Spacer(Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.rank),
                    color = MaterialTheme.colorScheme.onBackground.copy(.8f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}