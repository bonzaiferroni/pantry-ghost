package com.bonsai.pantryghost.ui.scan

import androidx.lifecycle.ViewModel
import com.google.mlkit.vision.text.Text
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.math.min

@HiltViewModel
class ScribeVm @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ScribeUiState())
    val uiState = _uiState.asStateFlow()

    fun onRecognizeText(text: Text) {
        val newLines = text.textBlocks.flatMap { it.lines }.toMutableList()
        val lines: MutableList<LineInfo> = mutableListOf()
        val matched: MutableList<Text.Line> = mutableListOf()

        // compare to cached lines
        for (lineInfo in uiState.value.lineInfos) {

            // try to find match
            val match = getClosestLevResult(lineInfo.line.text, newLines) { it.text }
            if (match != null) {
                val matchedLine = match.candidate
                matched.add(matchedLine)
                newLines.remove(matchedLine)

                // pick best line
                val bestLine = if (lineInfo.line.confidence > matchedLine.confidence) {
                    lineInfo.line
                } else {
                    matchedLine
                }

                val newInfo = lineInfo.copy(
                    line = bestLine, hit = true, hitCount = lineInfo.hitCount + 1
                )
                lines.add(newInfo)
            }
            else {
                // drop this line that has a lot of misses
                if (lineInfo.missCount > min(lineInfo.hitCount * 2, 20)) continue

                val newInfo = lineInfo.copy(
                    hit = false, missCount = lineInfo.missCount + 1
                )
                lines.add(newInfo)
            }
        }

        for (line in newLines) {
            // skip new lines that have been matched or low confidence
            if (matched.contains(line) || line.confidence < .5f) continue
            lines.add(LineInfo(
                line = line, hit = true, hitCount = 1, missCount = 0
            ))
        }

        _uiState.value = ScribeUiState(
            frameCount = uiState.value.frameCount + 1,
            lineInfos = lines.toList()
        )
    }

}

data class ScribeUiState(
    val frameCount: Int = 0,
    val lineInfos: List<LineInfo> = emptyList()
)

data class LineInfo(
    val line: Text.Line,
    val hit: Boolean,
    val hitCount: Int,
    val missCount: Int,
)