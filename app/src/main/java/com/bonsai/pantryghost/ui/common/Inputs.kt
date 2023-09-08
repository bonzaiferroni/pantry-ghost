package com.bonsai.pantryghost.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.bonsai.pantryghost.R
import com.chargemap.compose.numberpicker.ListItemPicker

@Composable
fun ValueField(
    value: String,
    label: String,
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

@Composable
fun ValuePickerField(
    text: String,
    pickerState: String,
    suggestions: List<String>,
    label: String,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.gap_large))
    ) {
        ValueField(
            value = text,
            label = label,
            keyboardType = keyboardType,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f)
        )

        ListItemPicker(
            label = { it },
            value = pickerState,
            onValueChange = onValueChange,
            list = suggestions,
            textStyle = TextStyle.Default.copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
            dividersColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.weight(1f)
        )
    }
}

const val pickerNullText = "?"

@Composable
fun StringField(
    text: String,
    pickerState: String,
    suggestions: List<String>,
    onValueChange: (String) -> Unit,
) {
    ValuePickerField(
        text = text,
        pickerState = pickerState,
        suggestions = suggestions,
        label = "Text",
        keyboardType = KeyboardType.Text,
        onValueChange = onValueChange
    )
}

@Composable
fun StringField(params: ValueFieldParams, onValueChange: (String) -> Unit) {
    StringField(params.text, params.pickerState, params.suggestions, onValueChange)
}

data class ValueFieldParams(
    val text: String = "",
    val pickerState: String = pickerNullText,
    val suggestions: List<String> = emptyList(),
) {
    constructor(text: String? = null, suggestions: List<String>? = null) : this(
        text = text ?: "",
        pickerState = text ?: pickerNullText,
        suggestions = suggestions ?: emptyList()
    )

    fun setText(text: String? = null): ValueFieldParams {
        return copy(
            text = text ?: "",
            pickerState = text ?: pickerNullText,
        )
    }
}

@Composable
fun FloatField(
    text: String,
    pickerState: String,
    suggestions: List<String>,
    onValueChange: (String) -> Unit,
) {
    ValuePickerField(
        text = text,
        pickerState = pickerState,
        suggestions = suggestions,
        label = "Decimal",
        keyboardType = KeyboardType.Decimal,
        onValueChange = onValueChange
    )
}

@Composable
fun IntegerField(
    text: String,
    pickerState: String,
    suggestions: List<String>,
    onValueChange: (String) -> Unit
) {
    ValuePickerField(
        text = text,
        pickerState = pickerState,
        suggestions = suggestions,
        label = "Integer",
        keyboardType = KeyboardType.Number,
        onValueChange = onValueChange
    )
}

@Composable
fun BooleanField(
    text: String,
    pickerState: String,
    onValueChange: (String) -> Unit
) {
    ValuePickerField(
        text = text,
        pickerState = pickerState,
        suggestions = listOf("false", pickerNullText, "true"),
        label = "Boolean",
        keyboardType = KeyboardType.Text,
        onValueChange = onValueChange
    )
}

@Composable
fun EnumField(
    text: String,
    pickerState: String,
    suggestions: List<String>,
    onValueChange: (String) -> Unit
) {
    ValuePickerField(
        text = text,
        pickerState = pickerState,
        suggestions = suggestions,
        label = "Enumerator",
        keyboardType = KeyboardType.Text,
        onValueChange = onValueChange
    )
}