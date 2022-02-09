package pl.mwaszczuk.hellocompose.raw.ui.main

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import pl.mwaszczuk.hellocompose.raw.ui.achievements.AchievementsScreen
import pl.mwaszczuk.hellocompose.raw.ui.dashboard.DashboardScreen
import pl.mwaszczuk.hellocompose.raw.ui.notifications.NotificationsScreen
import pl.mwaszczuk.hellocompose.raw.ui.profile.ProfileScreen
import pl.mwaszczuk.hellocompose.raw.ui.profile.ProfileViewModel
import pl.mwaszczuk.hellocompose.raw.ui.settings.SettingsScreen
import pl.mwaszczuk.hellocompose.util.*

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
            if (currentRoute.value != Destination.Achievements.route &&
                currentRoute.value != Destination.ChangeName.route
            ) {
                MainBottomBar(
                    navController = navController,
                    currentRoute = currentRoute.value
                )
            }
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
                    enterTransition = { initial, target ->
                        mainScreenEnterTransition(initial, target)
                    },
                    exitTransition = { initial, target ->
                        mainScreenExitTransition(initial, target)
                    }
                ) {
                    DashboardScreen()
                }

                navigation(
                    startDestination = Destination.Profile.route,
                    route = Destination.ProfileNavGraph.route
                ) {
                    composable(
                        route = Destination.Profile.route,
                        arguments = emptyList(),
                        deepLinks = emptyList(),
                        enterTransition = { initial, target ->
                            if (initial.destination.route == Destination.Achievements.route) {
                                slideIntoContainer(
                                    AnimatedContentScope.SlideDirection.Up,
                                    animationSpec = tween(400)
                                )
                            } else {
                                mainScreenEnterTransition(initial, target)
                            }
                        },
                        exitTransition = { initial, target ->
                            if (target.destination.route == Destination.Achievements.route) {
                                slideOutOfContainer(
                                    AnimatedContentScope.SlideDirection.Down,
                                    animationSpec = tween(400)
                                )
                            } else {
                                mainScreenExitTransition(initial, target)
                            }
                        }
                    ) {
                        val rootEntry =
                            remember { navController.getBackStackEntry(it.destination.parent?.id!!) }
                        val sharedViewModel = hiltViewModel<ProfileViewModel>(rootEntry)
                        ProfileScreen(
                            viewModel = sharedViewModel,
                            navController = navController
                        )
                    }
                    composable(
                        route = Destination.Achievements.route,
                        arguments = emptyList(),
                        deepLinks = emptyList(),
                        enterTransition = { _, _ ->
                            slideIntoContainer(
                                AnimatedContentScope.SlideDirection.Down,
                                animationSpec = tween(400)
                            )
                        },
                        exitTransition = { _, _ ->
                            slideOutOfContainer(
                                AnimatedContentScope.SlideDirection.Up,
                                animationSpec = tween(400)
                            )
                        }
                    ) {
                        val rootEntry =
                            remember { navController.getBackStackEntry(it.destination.parent?.id!!) }
                        val sharedViewModel = hiltViewModel<ProfileViewModel>(rootEntry)
                        AchievementsScreen(viewModel = sharedViewModel)
                    }
                }

                composable(
                    route = Destination.Notifications.route,
                    arguments = emptyList(),
                    deepLinks = emptyList(),
                    enterTransition = { initial, target ->
                        mainScreenEnterTransition(initial, target)
                    },
                    exitTransition = { initial, target ->
                        mainScreenExitTransition(initial, target)
                    }
                ) {
                    NotificationsScreen()
                }

                composable(
                    route = Destination.Settings.route,
                    arguments = emptyList(),
                    deepLinks = emptyList(),
                    enterTransition = { initial, target ->
                        mainScreenEnterTransition(initial, target)
                    },
                    exitTransition = { initial, target ->
                        mainScreenExitTransition(initial, target)
                    }
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
    Settings("SETTINGS_DESTINATION"),

    ChangeName("CHANGE_NAME_DESTINATION"),
    Achievements("ACHIEVEMENTS_DESTINATION")
}
