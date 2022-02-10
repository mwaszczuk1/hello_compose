package pl.mwaszczuk.hellocompose.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import pl.mwaszczuk.hellocompose.navigation.AppNavigationHandler
import pl.mwaszczuk.hellocompose.navigation.NavigationCommand
import pl.mwaszczuk.hellocompose.navigation.Navigator
import pl.mwaszczuk.hellocompose.navigation.destinations.MainNavGraph
import pl.mwaszczuk.hellocompose.navigation.destinations.ProfileNavGraph
import pl.mwaszczuk.hellocompose.raw.ui.main.Destination

@Composable
fun MainNavHostArchitected(navigator: Navigator) {
    val navController = rememberAnimatedNavController()
    val navigationCommand = navigator.navigationCommands.collectAsState(null)
    val currentRoute = (navigationCommand.value as? NavigationCommand.Navigate)?.navAction?.baseRoute
        ?: navController.currentBackStackEntryAsState().value?.destination?.route

    AppNavigationHandler(
        navigator = navigator,
        navController = navController
    )

    Scaffold(
        bottomBar = {
            if (currentRoute != ProfileNavGraph.ACHIEVEMENTS_DESTINATION &&
                currentRoute != MainNavGraph.ACCOUNT_DETAILS_DESTINATION
            ) {
                MainBottomBarArchitected(
                    navigator = navigator,
                    currentRoute = currentRoute
                )
            }
        }
    ) { padding ->
        AnimatedNavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            navController = navController,
            startDestination = MainNavGraph.DASHBOARD_DESTINATION,
            route = Destination.MainNavGraph.route
        ) {
            MainNavGraph().attachToNavHost(this)
            ProfileNavGraph(navController).attachToNavHost(this)
        }
    }
}
