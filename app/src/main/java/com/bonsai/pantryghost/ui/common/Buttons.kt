package com.bonsai.pantryghost.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.bonsai.pantryghost.utils.Gaps

@Composable
fun AcceptCancelButtons(
    enabled: Boolean,
    onAccept: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Gaps.large()),
        modifier = modifier
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

@Composable
fun PgIconButton(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentDescription: String? = null,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
        )
    }
}