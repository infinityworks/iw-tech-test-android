package com.infinity.foodstandards

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import com.infinity.foodstandards.ui.hygieneRating.RatingTable
import org.junit.Rule
import org.junit.Test

class RatingsComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val ratingPercent = "18%"
    private val exemptPercent = "10"
    private val ratings = mutableListOf<String>().apply {
        repeat(5) { add(ratingPercent) }
        add(exemptPercent)
    }

    @Test
    fun givenTableDisplayed_whenRatingsLoaded_thenAllRatingsShown() {
        composeTestRule.setContent {
            MaterialTheme {
                RatingTable(ratings)
            }
        }

        // TODO Should have 5 nodes with "18%" and 1 with "10%"
    }

    @Test
    fun givenTableDisplayed_whenRatingsLoaded_thenExemptPercentMatches() {
        composeTestRule.setContent {
            MaterialTheme {
                RatingTable(ratings)
            }
        }

        // TODO Assert table row with "Exempt" displays "10%"
    }
}
