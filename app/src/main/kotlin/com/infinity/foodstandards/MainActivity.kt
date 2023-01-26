package com.infinity.foodstandards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.infinity.foodstandards.ui.FoodStandardsTheme
import com.infinity.foodstandards.ui.NavGraph

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodStandardsTheme {
                Scaffold(
                    topBar = { FoodStandardsToolbar() }
                ) { padding ->
                    val navController = rememberNavController()
                    NavGraph(this, navController, Modifier.padding(padding))
                }
            }
        }
    }

    @Composable
    private fun FoodStandardsToolbar() {
        TopAppBar(
            title = {
                Text(stringResource(id = R.string.app_name))
            }
        )
    }
}
