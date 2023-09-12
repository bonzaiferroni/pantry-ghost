package com.bonsai.pantryghost.ui.scan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import com.google.mlkit.vision.text.Text

@Composable
fun BasicTextRecognition() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val extractedText = remember { mutableStateOf("") }
    val onRecognizeText: (Text) -> Unit =  {
        extractedText.value = it.text
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TextRecognitionView(
            context = context,
            lifecycleOwner = lifecycleOwner,
            onRecognizeText = onRecognizeText
        )
        Text(
            text = extractedText.value,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            color = Color.Black
        )
    }
}