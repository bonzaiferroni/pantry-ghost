package com.bonsai.pantryghost

import com.bonsai.pantryghost.data.SampleRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SampleRepositoryTest {
    @Test
    fun addition_isCorrect() = runTest {
        val sampleRepository = SampleRepository()
        val servings = sampleRepository.getServingsByMealId(1).first()
        assertEquals(1, servings.size)
    }
}