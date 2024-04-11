package com.maxidev.newsyork.core.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.maxidev.newsyork.R
import com.maxidev.newsyork.search.presentation.ArticleSearchScreen
import com.maxidev.newsyork.core.presentation.components.ComponentTopBar
import com.maxidev.newsyork.homenews.presentation.NewsWireScreen
import com.maxidev.newsyork.topnews.presentation.TopStoriesScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestinations: Destinations = Destinations.WireScreen
) {
    Scaffold(
        topBar = {
            ComponentTopBar(
                title = R.string.app_name
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = modifier
                    .height(70.dp),
                tonalElevation = NavigationBarDefaults.Elevation
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val items = listOf(
                    Destinations.WireScreen,
                    Destinations.SearchArticleScreen,
                    Destinations.StoriesScreen
                )

                items.forEach { screen ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = screen.icons,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = stringResource(id = screen.label))
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .padding(paddingValues),
            navController = navController,
            startDestination = startDestinations.route
        ) {
            composable(route = startDestinations.route) {
                NewsWireScreen()
            }
            composable(route = Destinations.SearchArticleScreen.route) {
                ArticleSearchScreen()
            }
            composable(route = Destinations.StoriesScreen.route) {
                TopStoriesScreen()
            }
        }
    }
}