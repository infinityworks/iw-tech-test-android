package com.infinity.foodstandards

import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.fragment.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.infinity.foodstandards.model.LocalAuthority
import com.infinity.foodstandards.ui.authorities.AuthorityList
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

class AuthoritiesFragmentTest {

    private val city1 = "Aberdeen City"
    private val city2 = "Amber Valley"
    private val localAuthorities = listOf(
        LocalAuthority(city1, 1),
        LocalAuthority(city2, 2)
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenDisplayingListWhenDataPopulatedThenCityShows() {
        composeTestRule.setContent {
            MaterialTheme {
                AuthorityList(localAuthorities) {
                }
            }
        }
        composeTestRule.onNodeWithText(city1).assertIsDisplayed()
        composeTestRule.onNodeWithText(city2).assertIsDisplayed()
    }

    @Test
    fun givenDisplayingListWhenItemClickedThenCallbackInvoked() {
        val callbackOnClick: () -> Unit = Mockito.mock(OnClickCallback::class.java)
        composeTestRule.setContent {
            MaterialTheme {
                AuthorityList(localAuthorities, callbackOnClick)
            }
        }
        composeTestRule.onNodeWithText(city1).performClick()
        val verifyCallback = Mockito.verify(callbackOnClick, Mockito.times(1))
        verifyCallback()
    }
    interface OnClickCallback: () -> Unit
//    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
}
