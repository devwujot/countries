package com.decwujot.countires.presentation

import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decwujot.countires.R
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CountriesFeature : BaseUITest() {

    @Test
    fun displayTheLoaderWhileFetchingTheCountries() {
        IdlingRegistry.getInstance().unregister(idlingResource)

        Thread.sleep(3100)

        assertDisplayed(R.id.countries_progress)
    }

    @Test
    fun hideLoader() {

        Thread.sleep(4100)

        assertNotDisplayed(R.id.countries_progress)
    }

    @Test
    fun navigateToCountryDetail() {

        Thread.sleep(4100)

        navigate(1)

        assertDisplayed(R.id.fragment_detail_root)
    }


}