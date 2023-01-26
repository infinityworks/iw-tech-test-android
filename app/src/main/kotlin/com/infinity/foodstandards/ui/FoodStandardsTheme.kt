package com.infinity.foodstandards.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val foodStandards_Orange = Color(0xFFE94E1C)
private val foodStandards_Grey = Color(0xFF4A4D53)
private val foodStandards_Blue = Color(0xFF4DC1EB)

val foodStandardsColours = lightColors(
    primary = foodStandards_Orange,
    secondary = foodStandards_Blue,
    primaryVariant = foodStandards_Grey
)

@Composable
fun FoodStandardsTheme(content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = foodStandards_Orange)

    MaterialTheme(colors = foodStandardsColours) {
        content()
    }
}
