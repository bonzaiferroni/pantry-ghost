package com.bonsai.pantryghost.ui.scan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.bonsai.pantryghost.utils.gapMedium
import com.bonsai.pantryghost.utils.paddingSmall

@Composable
fun ScribeScreen(
    modifier: Modifier = Modifier,
    viewModel: ScribeVm = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .padding(paddingSmall()),
        verticalArrangement = Arrangement.spacedBy(gapMedium())
    ) {
        TextRecognitionView(
            context = LocalContext.current,
            lifecycleOwner = LocalLifecycleOwner.current,
            onRecognizeText = viewModel::onRecognizeText
        )
        Text(text = "Frames: ${uiState.frameCount}")
        uiState.lineInfos.forEach { lineInfo ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                val confidence = (lineInfo.line.confidence * 100).toInt()
                Text(text = lineInfo.line.text, maxLines = 1)
                Text(
                    text = "($confidence) ${lineInfo.hitCount} / ${lineInfo.missCount}",
                    maxLines = 1
                )
            }
        }
    }
}