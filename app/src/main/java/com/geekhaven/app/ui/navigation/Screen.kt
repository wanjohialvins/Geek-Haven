package com.geekhaven.app.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Library : Screen("library")
    object BookHub : Screen("book_hub/{bookId}") {
        fun createRoute(bookId: Long) = "book_hub/$bookId"
    }
    object Reader : Screen("reader/{bookId}/{format}") {
        fun createRoute(bookId: Long, format: String) = "reader/$bookId/$format"
    }
    object Settings : Screen("settings")
    object Perms : Screen("permissions") // Initial setup
}
