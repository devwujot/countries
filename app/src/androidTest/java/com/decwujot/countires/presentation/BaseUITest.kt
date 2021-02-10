package com.decwujot.countires.presentation

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.decwujot.countires.R
import com.decwujot.countires.presentation.activities.MainActivity
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.OkHttpClient
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

@RunWith(AndroidJUnit4::class)
abstract class BaseUITest {

    val mActivity = ActivityTestRule(MainActivity::class.java)
        @Rule get

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    // utility method to compare recyclerView items
    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }

    fun navigate(row: Int) {
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.item_image),
                ViewMatchers.isDescendantOfA(
                    nthChildOf(
                        ViewMatchers.withId(R.id.countries_list),
                        row
                    )
                )
            )
        )
            .perform(ViewActions.click())
    }
}