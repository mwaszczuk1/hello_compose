package pl.mwaszczuk.hellocompose.raw.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import pl.mwaszczuk.hellocompose.raw.ui.dashboard.DashboardScreen
import pl.mwaszczuk.hellocompose.raw.ui.notifications.NotificationsScreen
import pl.mwaszczuk.hellocompose.raw.ui.profile.ProfileScreen
import pl.mwaszczuk.hellocompose.raw.ui.settings.SettingsScreen
import pl.mwaszczuk.hellocompose.util.getMainScreenEnterTransition
import pl.mwaszczuk.hellocompose.util.getMainScreenExitTransition

@Composable
fun MainNavHost() {
    val navController = rememberAnimatedNavController()

    val currentRoute = remember {
        mutableStateOf(navController.currentBackStackEntry?.destination?.route)
    }

    LaunchedEffect(key1 = true) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentRoute.value = destination.route
        }
    }

    Scaffold(
        bottomBar = {
            MainBottomBar(
                navController = navController,
                currentRoute = currentRoute.value
            )
        }
    ) { scaffoldPaddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPaddings)
        ) {
            AnimatedNavHost(
                navController = navController,
                startDestination = Destination.Dashboard.route,
                route = Destination.MainNavGraph.route
            ) {
                composable(
                    route = Destination.Dashboard.route,
                    arguments = emptyList(),
                    deepLinks = emptyList(),
                    enterTransition = getMainScreenEnterTransition(),
                    exitTransition = getMainScreenExitTransition()
                ) {
                    DashboardScreen()
                }

                navigation(
                    startDestination = Destination.Profile.route,
                    route = Destination.ProfileNavGraph.route
                ) {
                    composable(
                        route = "PROFILE_DESTINATION",
                        arguments = emptyList(),
                        deepLinks = emptyList(),
                        enterTransition = getMainScreenEnterTransition(),
                        exitTransition = getMainScreenExitTransition()
                    ) {
                        ProfileScreen()
                    }
                }

                composable(
                    route = Destination.Notifications.route,
                    arguments = emptyList(),
                    deepLinks = emptyList(),
                    enterTransition = getMainScreenEnterTransition(),
                    exitTransition = getMainScreenExitTransition()
                ) {
                    NotificationsScreen()
                }

                composable(
                    route = Destination.Settings.route,
                    arguments = emptyList(),
                    deepLinks = emptyList(),
                    enterTransition = getMainScreenEnterTransition(),
                    exitTransition = getMainScreenExitTransition()
                ) {
                    SettingsScreen()
                }
            }
        }
    }
}

enum class Destination(val route: String) {
    MainNavGraph("MAIN_NAV_GRAPH_DESTINATION"),
    ProfileNavGraph("PROFILE_NAV_GRAPH_DESTINATION"),
    Dashboard("DASHBOARD_DESTINATION"),
    Profile("PROFILE_DESTINATION"),
    Notifications("NOTIFICATIONS_DESTINATION"),
    Settings("SETTINGS_DESTINATION")
}
