package com.mdubovikov.narutodb.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mdubovikov.narutodb.R
import com.mdubovikov.narutodb.domain.entity.CharacterDetails

@Composable
fun Jutsu(
    isAvailableList: Boolean,
    details: CharacterDetails
) {
    if (!isAvailableList) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = stringResource(R.string.jutsu),
                    fontSize = 24.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            repeat(
                details.jutsu?.size ?: 0,
                action = {
                    Text(
                        text = "- " + (details.jutsu?.get(it) ?: 0).toString(),
                        fontSize = 14.sp
                    )
                }
            )
        }
    }
}