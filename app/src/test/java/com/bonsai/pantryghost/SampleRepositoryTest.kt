package com.bonsai.pantryghost

import com.bonsai.pantryghost.data.SampleData
import com.bonsai.pantryghost.data.SampleRepository
import com.bonsai.pantryghost.utils.toLocalDate
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SampleRepositoryTest {
    @Test
    fun getServingAmountsById_returnsCorrectSize() = runTest {
        val sampleRepository = SampleRepository()
        val servings = sampleRepository.getServingAmountsById(1).first()
        assertEquals(1, servings.size)
    }

    @Test
    fun getServingsOnDate_returnsCorrectSize() = runTest {
        val targetSize = SampleData.mealTimes
            .filter { it.time.toLocalDate() == LocalDate.now() }.size
        val sampleRepository = SampleRepository()
        val servings = sampleRepository.getServingsOnDate(LocalDate.now()).first()
        assertEquals(targetSize, servings.size)
    }
}