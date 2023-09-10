package com.bonsai.pantryghost.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import com.bonsai.pantryghost.utils.gapLarge
import com.chargemap.compose.numberpicker.ListItemPicker

const val nullPickerText = "?"

@Composable
fun ValuePickerField(
    value: String,
    pickerState: String,
    suggestions: List<String>,
    label: String,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(gapLarge()),
        modifier = modifier,
    ) {
        ValueField(
            value = value,
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

@Composable
fun StringPickerField(
    label: String,
    value: String,
    pickerState: String,
    suggestions: List<String>,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    ValuePickerField(
        value = value,
        pickerState = pickerState,
        suggestions = suggestions,
        label = label,
        keyboardType = KeyboardType.Text,
        onValueChange = onValueChange,
        modifier = modifier,
    )
}

@Composable
fun StringPickerField(label: String, params: ValuePickerParams, onValueChange: (String) -> Unit) {
    StringPickerField(label, params.value, params.pickerState, params.suggestions, onValueChange)
}

data class ValuePickerParams(
    val value: String = "",
    val pickerState: String = nullPickerText,
    val suggestions: List<String> = emptyList(),
) {
    constructor(text: String? = null, suggestions: List<String>? = null) : this(
        value = text ?: "",
        pickerState = text ?: nullPickerText,
        suggestions = suggestions ?: emptyList()
    )

    fun setText(text: String? = null): ValuePickerParams {
        return copy(
            value = text ?: "",
            pickerState = text ?: nullPickerText,
        )
    }
}

@Composable
fun FloatPickerField(
    label: String,
    value: String,
    pickerState: String,
    suggestions: List<String>,
    onValueChange: (String) -> Unit,
) {
    ValuePickerField(
        value = value,
        pickerState = pickerState,
        suggestions = suggestions,
        label = label,
        keyboardType = KeyboardType.Decimal,
        onValueChange = onValueChange
    )
}

@Composable
fun IntegerPickerField(
    value: String,
    pickerState: String,
    suggestions: List<String>,
    onValueChange: (String) -> Unit
) {
    ValuePickerField(
        value = value,
        pickerState = pickerState,
        suggestions = suggestions,
        label = "Integer",
        keyboardType = KeyboardType.Number,
        onValueChange = onValueChange
    )
}

@Composable
fun BooleanPickerField(
    value: String,
    pickerState: String,
    onValueChange: (String) -> Unit
) {
    ValuePickerField(
        value = value,
        pickerState = pickerState,
        suggestions = listOf("false", nullPickerText, "true"),
        label = "Boolean",
        keyboardType = KeyboardType.Text,
        onValueChange = onValueChange
    )
}

@Composable
fun EnumPickerField(
    value: String,
    pickerState: String,
    suggestions: List<String>,
    onValueChange: (String) -> Unit
) {
    ValuePickerField(
        value = value,
        pickerState = pickerState,
        suggestions = suggestions,
        label = "Enumerator",
        keyboardType = KeyboardType.Text,
        onValueChange = onValueChange
    )
}

@Composable
fun <T> ItemPicker(
    pickerState: T,
    suggestions: List<T>,
    modifier: Modifier = Modifier,
    onValueChange: (T) -> Unit
) {
    ListItemPicker(
        label = { it.toString() },
        value = pickerState,
        onValueChange = onValueChange,
        list = suggestions,
        textStyle = TextStyle.Default.copy(
            color = MaterialTheme.colorScheme.onBackground
        ),
        dividersColor = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}