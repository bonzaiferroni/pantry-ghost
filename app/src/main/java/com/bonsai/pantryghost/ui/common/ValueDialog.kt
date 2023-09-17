package com.bonsai.pantryghost.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bonsai.pantryghost.utils.Paddings

@Composable
fun ValueDialog(
    showDialog: Boolean,
    label: String,
    value: String?,
    modifier: Modifier = Modifier,
    onDismiss: ((isAccepted: Boolean) -> Unit),
    onValueChange: (String) -> Unit,
) {
    if (!showDialog) return

    Dialog(
        onDismissRequest = { onDismiss(false) },
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(Paddings.medium()),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(Paddings.small()),
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(Paddings.small()),
            ) {
                ValueField(
                    label = label,
                    value = value,
                    onValueChange = onValueChange,
                )
                AcceptCancelButtons(
                    onAccept = { onDismiss(true) },
                    onCancel = { onDismiss(false) },
                    enabled = value?.isNotBlank() ?: false,
                )
            }
        }
    }
}

@Preview
@Composable
fun ValueDialogPreview() {
    ValueDialog(
        showDialog = true,
        label = "Label",
        value = "Value",
        onDismiss = {},
        onValueChange = {},
    )
}