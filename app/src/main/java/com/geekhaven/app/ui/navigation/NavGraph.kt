package com.geekhaven.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.geekhaven.app.ui.bookhub.BookHubScreen
import com.geekhaven.app.ui.home.HomeScreen
import com.geekhaven.app.ui.library.LibraryScreen
import com.geekhaven.app.ui.settings.SettingsScreen

@Composable
fun GeekHavenNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToLibrary = { navController.navigate(Screen.Library.route) },
                onNavigateToBook = { bookId ->
                    navController.navigate(Screen.BookHub.createRoute(bookId))
                }
            )
        }

        composable(Screen.Library.route) {
            LibraryScreen(
                onNavigateToBook = { bookId ->
                    navController.navigate(Screen.BookHub.createRoute(bookId))
                }
            )
        }

        composable(
            route = Screen.BookHub.route,
            arguments = listOf(navArgument("bookId") { type = NavType.LongType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getLong("bookId") ?: 0L
            BookHubScreen(
                bookId = bookId,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
