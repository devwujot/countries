package com.decwujot.countires.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decwujot.countires.R
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import kotlinx.android.synthetic.main.fragment_countries.view.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashScreenFeature : BaseUITest() {

    @Test
    fun displayAppLogo() {

        assertDisplayed("Countries App")
    }

    @Test
    fun navigateToCountriesListView() {

        Thread.sleep(3100L)

        assertDisplayed(R.id.fragment_countries_root)
    }
}