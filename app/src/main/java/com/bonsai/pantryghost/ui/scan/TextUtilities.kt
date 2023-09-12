package com.bonsai.pantryghost.ui.scan

import kotlin.math.abs

fun levenshtein(str1: String, str2: String): Int {
    val dp = Array(str1.length + 1) { IntArray(str2.length + 1) }

    for (i in 0..str1.length) {
        for (j in 0..str2.length) {
            when {
                i == 0 -> dp[i][j] = j
                j == 0 -> dp[i][j] = i
                else -> dp[i][j] = minOf(
                    dp[i - 1][j - 1] + if (str1[i - 1] == str2[j - 1]) 0 else 1,
                    dp[i - 1][j] + 1,
                    dp[i][j - 1] + 1
                )
            }
        }
    }

    return dp[str1.length][str2.length]
}

const val lengthDifferenceThreshold = .25f
const val ratioThreshold = .5f

fun <T> getClosestLevResult(
    target: String, candidates: List<T>, selector: (T) -> String
): LevResult<T>? {
    var closest: LevResult<T>? = null

    for (candidate in candidates) {
        val text = selector(candidate)
        val lengthDifference = abs(target.length - text.length) / target.length.toFloat()
        if (lengthDifference > lengthDifferenceThreshold) continue
        val distance = levenshtein(target, text)
        val ratio = distance / text.length.toFloat()
        if (ratio > ratioThreshold) continue
        if (closest != null && closest.ratio < ratio) continue
        closest = LevResult(distance, ratio, lengthDifference, candidate)
    }
    return closest
}

data class LevResult<T>(
    val distance: Int,
    val ratio: Float,
    val lengthDifference: Float,
    val candidate: T,
)