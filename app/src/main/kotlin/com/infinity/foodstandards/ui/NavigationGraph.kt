package com.infinity.foodstandards.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.infinity.foodstandards.ui.authorities.AuthoritiesScreen
import com.infinity.foodstandards.ui.authorities.AuthoritiesViewModel
import com.infinity.foodstandards.ui.hygieneRating.HygieneRatingsScreen
import com.infinity.foodstandards.ui.hygieneRating.HygieneRatingsViewModel

@Composable
internal fun NavGraph(
    viewModelStoreOwner: ViewModelStoreOwner,
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Authorities.path(),
        modifier = modifier
    ) {
        composable(Routes.Authorities.path()) {
            val authoritiesViewModel = ViewModelProvider(viewModelStoreOwner)[AuthoritiesViewModel::class.java]
            AuthoritiesScreen(viewModel = authoritiesViewModel) {
                navController.navigate(
                    Routes.HygieneRatings.path()
                )
            }
        }
        composable(
            route = Routes.HygieneRatings.path(Routes.HygieneRatings.authorityId),
            arguments = listOf(navArgument(Routes.HygieneRatings.authorityId) { type = NavType.StringType })
        ) {
            val hygieneRatingsViewModel = ViewModelProvider(viewModelStoreOwner)[HygieneRatingsViewModel::class.java]
            HygieneRatingsScreen(
                viewModel = hygieneRatingsViewModel,
                localAuthorityId = "0"
            )
        }
    }
}

sealed class Routes(private val route: String) {
    object Authorities : Routes("Authorities")

    object HygieneRatings : Routes("HygieneRatings") {
        const val authorityId: String = "authorityId"
    }

    // Creates a route with arguments for actual navigation
    fun withArgs(vararg args: String) = buildString {
        append(route)
        args.forEach { arg ->
            append("/$arg")
        }
    }

    // Creates a path with argument names for the NavGraphBuilder
    fun path(vararg args: String) = buildString {
        append(route)
        args.forEach { arg ->
            append("/{$arg}")
        }
    }
}
