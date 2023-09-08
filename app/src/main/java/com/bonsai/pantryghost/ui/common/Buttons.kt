package com.bonsai.pantryghost.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.bonsai.pantryghost.R

@Composable
fun AcceptCancelButtons(
    enabled: Boolean,
    onAccept: () -> Unit,
    onCancel: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.gap_medium)),
        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
    ) {
        Button(
            onClick = onCancel,
            modifier = Modifier.weight(1f),
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "cancel"
            )
        }
        Button(
            onClick = onAccept,
            modifier = Modifier.weight(1f),
            enabled = enabled,
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "accept"
            )
        }
    }
}