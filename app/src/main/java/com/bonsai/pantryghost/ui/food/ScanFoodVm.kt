package com.bonsai.pantryghost.ui.food

import com.bonsai.pantryghost.data.DataRepository
import com.bonsai.pantryghost.data.usda.UsdaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanFoodVm @Inject constructor(
    dataRepository: DataRepository,
    usdaRepository: UsdaRepository,
) {

}
