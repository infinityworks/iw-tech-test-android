package com.infinity.foodstandards


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RatingsFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun ratingsFragmentTest() {
        val linearLayout = onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.authoritiesRecycleView),
                        childAtPosition(
                            withId(R.id.main),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        linearLayout.perform(click())

        Thread.sleep(2000)

        val textView = onView(
            allOf(
                withId(R.id.percentage5TextView), withText("28.6%"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("28.6%")))

        val textView2 = onView(
            allOf(
                withId(R.id.percentage4TextView), withText("14.3%"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("14.3%")))

        val textView3 = onView(
            allOf(
                withId(R.id.percentage3TextView), withText("14.3%"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container),
                        0
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("14.3%")))

        val textView4 = onView(
            allOf(
                withId(R.id.percentage2TextView), withText("14.3%"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container),
                        0
                    ),
                    9
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("14.3%")))

        val textView5 = onView(
            allOf(
                withId(R.id.percentage1TextView), withText("14.3%"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container),
                        0
                    ),
                    11
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("14.3%")))

        val textView6 = onView(
            allOf(
                withId(R.id.percentageExemptTextView), withText("14.3%"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container),
                        0
                    ),
                    13
                ),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("14.3%")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
