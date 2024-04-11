package com.maxidev.newsyork.core.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.maxidev.newsyork.R

sealed class Destinations(
    val route: String,
    val icons: ImageVector,
    @StringRes val label: Int
) {
    data object WireScreen: Destinations(
        route = "wire_screen",
        icons = Icons.Outlined.Home,
        label = R.string.home_nav
    )
    data object SearchArticleScreen: Destinations(
        route = "search_article_screen",
        icons = Icons.Outlined.Search,
        label = R.string.search_nav
    )
    data object StoriesScreen: Destinations(
        route = "stories_screen",
        icons = Icons.Outlined.Star,
        label = R.string.top_stories_nav
    )
}