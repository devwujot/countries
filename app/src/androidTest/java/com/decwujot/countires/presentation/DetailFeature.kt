package com.decwujot.countires.presentation

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.decwujot.countires.R
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailFeature : BaseUITest() {

    @Test
    fun displayNameAndFlag() {

        IdlingRegistry.getInstance().unregister(idlingResource)

        Thread.sleep(4100)

        navigate(0)

        Thread.sleep(2000)

        assertDisplayed(R.id.detail_image)
        assertDisplayed("Afghanistan")
        assertDisplayed("Kabul")
    }
}