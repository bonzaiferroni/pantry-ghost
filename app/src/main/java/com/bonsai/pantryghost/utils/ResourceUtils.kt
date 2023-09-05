package com.bonsai.pantryghost.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.bonsai.pantryghost.R

@Composable
fun paddingSmall(): Dp = dimensionResource(R.dimen.padding_small)

@Composable
fun paddingMedium(): Dp = dimensionResource(R.dimen.padding_medium)

@Composable
fun paddingLarge(): Dp = dimensionResource(R.dimen.padding_small)

@Composable
fun gapSmall(): Dp = dimensionResource(R.dimen.gap_small)

@Composable
fun gapMedium(): Dp = dimensionResource(R.dimen.gap_medium)

@Composable
fun gapLarge(): Dp = dimensionResource(R.dimen.gap_large)