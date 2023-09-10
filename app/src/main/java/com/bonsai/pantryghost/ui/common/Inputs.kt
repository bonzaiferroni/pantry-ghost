package com.bonsai.pantryghost.ui.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun StringField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    ValueField(
        value = value,
        label = label,
        keyboardType = KeyboardType.Text,
        onValueChange = onValueChange,
        modifier = modifier,
    )
}

@Composable
fun FloatField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    ValueField(
        value = value,
        label = label,
        keyboardType = KeyboardType.Decimal,
        onValueChange = onValueChange,
        modifier = modifier,
    )
}

@Composable
fun ValueField(
    label: String,
    value: String,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        ),
        modifier = modifier
    )
}