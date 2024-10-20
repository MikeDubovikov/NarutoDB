package com.mdubovikov.narutodb.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
    details: CharacterDetails,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.family),
        fontSize = 24.sp
    )

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.outline.copy(.2f))
                .clip(MaterialTheme.shapes.large)
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Row {
                Text(
                    text = stringResource(R.string.mother)
                )
            }

            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(30.dp)
                    .background(color = MaterialTheme.colorScheme.outline)
            )

            Row {
                Text(
                    text = if (!details.mother.isNullOrEmpty()) {
                        details.mother
                    } else {
                        stringResource(R.string.empty)
                    }
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.outline.copy(.2f))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.father)
            )

            Text(
                text = if (!details.father.isNullOrEmpty()) {
                    details.father
                } else {
                    stringResource(R.string.empty)
                }
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.outline.copy(.2f))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.sister)
            )

            Text(
                text = if (!details.sister.isNullOrEmpty()) {
                    details.sister
                } else {
                    stringResource(R.string.empty)
                }
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.outline.copy(.2f))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.brother)
            )

            Text(
                text = if (!details.brother.isNullOrEmpty()) {
                    details.brother
                } else {
                    stringResource(R.string.empty)
                }
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.outline.copy(.2f))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.daughter)
            )

            Text(
                text = if (!details.daughter.isNullOrEmpty()) {
                    details.daughter
                } else {
                    stringResource(R.string.empty)
                }
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.outline.copy(.2f))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.son)
            )

            Text(
                text = if (!details.son.isNullOrEmpty()) {
                    details.son
                } else {
                    stringResource(R.string.empty)
                }
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.outline.copy(.2f))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.wife)
            )

            Text(
                text = if (!details.wife.isNullOrEmpty()) {
                    details.wife
                } else {
                    stringResource(R.string.empty)
                }
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.outline.copy(.2f))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.husband)
            )

            Text(
                text = if (!details.husband.isNullOrEmpty()) {
                    details.husband
                } else {
                    stringResource(R.string.empty)
                }
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.outline.copy(.2f))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.grandmother)
            )

            Text(
                text = if (!details.grandmother.isNullOrEmpty()) {
                    details.grandmother
                } else {
                    stringResource(R.string.empty)
                }
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.outline.copy(.2f))
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.grandfather)
            )

            Text(
                text = if (!details.grandfather.isNullOrEmpty()) {
                    details.grandfather
                } else {
                    stringResource(R.string.empty)
                }
            )
        }
    }
}