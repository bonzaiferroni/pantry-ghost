package com.bonsai.pantryghost

import android.app.Application
import com.bonsai.pantryghost.data.DataRepository
import com.bonsai.pantryghost.data.SampleRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class PgApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Main.immediate)

    @Inject
    lateinit var dataRepository: DataRepository

    override fun onCreate() {
        super.onCreate()

        applicationScope.launch {
            val sampleRepository = SampleRepository()

            val foods = dataRepository.getAllFoods().first()
            if (foods.isEmpty()) {
                val sampleFoods = sampleRepository.getAllFoods().first()
                dataRepository.insertFoods(sampleFoods)
            }

            val mealTypes = dataRepository.getAllMealTypes().first()
            if (mealTypes.isEmpty()) {
                val sampleMealTypes = sampleRepository.getAllMealTypes().first()
                dataRepository.insertMealTypes(sampleMealTypes)
            }
        }
    }
}