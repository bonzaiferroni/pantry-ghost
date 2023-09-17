package com.bonsai.pantryghost.ui.common

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bonsai.pantryghost.utils.Paddings

@Composable
fun PgDialog(
    showDialog: Boolean,
    modifier: Modifier = Modifier,
    onDismiss: (() -> Unit)?,
    content: @Composable ColumnScope.() -> Unit,
) {
    if (!showDialog) return

    Dialog(
        onDismissRequest = { onDismiss?.invoke() },
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(Paddings.medium()),
            shape = RoundedCornerShape(16.dp),
        ) {
            content()
        }
    }
}