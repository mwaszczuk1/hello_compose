package pl.mwaszczuk.hellocompose.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

abstract class NavigationGraph {
    abstract val route: String
    abstract val startDestination: String

    protected fun NavGraphBuilder.createDestination(
        destination: NavigationAction,
        content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
    ) {
        composable(destination, content)
    }

    @Composable
    protected inline fun <reified VM : ViewModel> parentViewModel(
        entry: NavBackStackEntry,
        navController: NavController
    ): VM {
        val rootEntry = remember { navController.getBackStackEntry(entry.destination.parent?.id!!) }
        return hiltViewModel<VM>(rootEntry)
    }
}
