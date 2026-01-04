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
                },
                onNavigateToSearch = { navController.navigate(Screen.Search.route) }
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

        composable(
            route = Screen.Reader.route,
            arguments = listOf(
                navArgument("bookId") { type = NavType.LongType },
                navArgument("format") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getLong("bookId") ?: 0L
            val format = backStackEntry.arguments?.getString("format") ?: "unknown"
            com.geekhaven.app.ui.reader.ReaderScreen(
                bookId = bookId,
                format = format,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        
        composable(Screen.Search.route) {
             com.geekhaven.app.ui.search.SearchScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToBook = { bookId -> 
                    // If book is saved, go to Hub. If remote, maybe show different view?
                    // For now, assume we implement "Add" logic later or just view Hub if ID exists.
                    if (bookId > 0) navController.navigate(Screen.BookHub.createRoute(bookId))
                }
             )
        }
    }
}
