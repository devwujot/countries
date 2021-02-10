package com.decwujot.countires.framework.viewModel

import com.decwujot.countires.framework.UseCases
import com.decwujot.countires.utils.BaseUnitTest
import com.decwujot.countires.utils.captureValues
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import retrofit2.Response

class CountriesViewModelShould : BaseUnitTest() {

    private lateinit var viewModel: CountriesViewModel
    val useCases = UseCases(mock(), mock())


    @Test
    fun closeLoaderWhenFetchedCountries() = runBlockingTest {

        mockSuccessfulCase()

        viewModel.isLoading.captureValues {
            viewModel.fetchCountries()

            assertEquals(false, values.last())
        }

    }

    private suspend fun mockSuccessfulCase() {
        whenever(useCases.fetchAllCountries()).thenReturn(
            Response.success(listOf())
        )
        viewModel = CountriesViewModel(useCases)
    }
}