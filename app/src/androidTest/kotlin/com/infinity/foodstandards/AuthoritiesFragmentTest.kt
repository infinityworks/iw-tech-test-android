package com.infinity.foodstandards

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
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
class AuthoritiesFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun authoritiesFragmentTest() {
        val textView = onView(
            allOf(
                withId(R.id.authorityNameTextView),
                withText("Aberdeen City"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.authoritiesRecycleView),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Aberdeen City")))

        val textView2 = onView(
            allOf(
                withId(R.id.authorityNameTextView),
                withText("Amber Valley"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.authoritiesRecycleView),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Amber Valley")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>,
        position: Int
    ): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent) &&
                    view == parent.getChildAt(position)
            }
        }
    }
}
