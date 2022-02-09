package pl.mwaszczuk.hellocompose.raw.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import pl.mwaszczuk.hellocompose.raw.ui.dashboard.DashboardScreen
import pl.mwaszczuk.hellocompose.raw.ui.notifications.NotificationsScreen
import pl.mwaszczuk.hellocompose.raw.ui.profile.ProfileScreen
import pl.mwaszczuk.hellocompose.raw.ui.settings.SettingsScreen

@Composable
fun MainNavHost() {
    val navController = rememberAnimatedNavController()

    Scaffold(
        bottomBar = {
            MainBottomBar(
                navController = navController
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
                startDestination = "DASHBOARD_DESTINATION",
                route = "MAIN_NAV_GRAPH_DESTINATION"
            ) {
                composable(
                    route = "DASHBOARD_DESTINATION",
                    arguments = emptyList(),
                    deepLinks = emptyList()
                ) {
                    DashboardScreen()
                }

                navigation(
                    startDestination = "PROFILE_DESTINATION",
                    route = "PROFILE_NAV_GRAPH_DESTINATION"
                ) {
                    composable(
                        route = "PROFILE_DESTINATION",
                        arguments = emptyList(),
                        deepLinks = emptyList()
                    ) {
                        ProfileScreen()
                    }
                }

                composable(
                    route = "NOTIFICATIONS_DESTINATION",
                    arguments = emptyList(),
                    deepLinks = emptyList()
                ) {
                    NotificationsScreen()
                }

                composable(
                    route = "SETTINGS_DESTINATION",
                    arguments = emptyList(),
                    deepLinks = emptyList()
                ) {
                    SettingsScreen()
                }
            }
        }
    }
}
