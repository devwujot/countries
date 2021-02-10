package com.decwujot.countires.framework.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.decwujot.countires.utils.BaseUnitTest
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashScreenViewModelShould : BaseUnitTest() {

    private lateinit var viewModel: SplashScreenViewModel

    @Before
    fun setUp() {
        viewModel = SplashScreenViewModel()
    }

    @Test
    fun goToCountriesView() {

        viewModel.goToCountriesFragment()

        assertEquals(true, viewModel.isGoing.value)
    }
}