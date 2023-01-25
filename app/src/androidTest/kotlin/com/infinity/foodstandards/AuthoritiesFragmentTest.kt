package com.infinity.foodstandards

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.infinity.foodstandards.model.LocalAuthority
import com.infinity.foodstandards.ui.authorities.AuthorityList
import org.junit.Rule
import org.junit.Test
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
    interface OnClickCallback : () -> Unit
//    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
}
