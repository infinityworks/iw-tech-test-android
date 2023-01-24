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
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class AuthoritiesComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val city1 = "Aberdeen City"
    private val city2 = "Amber Valley"
    private val localAuthorities = listOf(
        LocalAuthority(city1, 1),
        LocalAuthority(city2, 2)
    )

    @Test
    fun givenDisplayingList_whenDataPopulated_thenCityShows() {
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
    fun givenDisplayingList_whenItemClicked_thenCallbackInvoked() {
        val callbackOnClick: () -> Unit = mock()
        composeTestRule.setContent {
            MaterialTheme {
                AuthorityList(localAuthorities, callbackOnClick)
            }
        }
        composeTestRule.onNodeWithText(city1).performClick()
        verify(callbackOnClick, times(1)).invoke()
    }
}
