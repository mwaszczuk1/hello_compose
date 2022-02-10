package pl.mwaszczuk.hellocompose.navigation

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import pl.mwaszczuk.hellocompose.extensions.startActivity
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun AppNavigationHandler(
    navigator: Navigator,
    navController: NavHostController
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        navigator.navigationCommands.collectLatest {
            execute(context, navigator, navController, it)
        }
    }
}

@ExperimentalMaterialNavigationApi
private suspend fun execute(
    context: Context,
    navigator: Navigator,
    navController: NavHostController,
    navCommand: NavigationCommand
) {
    when (navCommand) {
        NavigationCommand.Back -> {
            val backStackCount = navController.backQueue.count { entry ->
                entry.destination !is NavGraph
            }
            if (backStackCount == 1) {
                (context as? Activity)?.finish()
            } else {
                navController.navigateUp()
            }
        }
        is NavigationCommand.Navigate -> {
            navigator.onNavigationChanged(navCommand.navAction)
            if (navController.currentDestination?.route != navCommand.navAction.route) {
                navController.navigate(
                    route = navCommand.navRoute,
                    navOptions = navCommand.navOptions,
                    navigatorExtras = navCommand.navAction.navigatorExtras
                )
            }
        }
        is NavigationCommand.PopUpTo -> navController.popBackStack(
            navCommand.route,
            navCommand.inclusive
        )
        is NavigationCommand.StartActivity<*> -> {
            context.startActivity(
                activity = navCommand.activity,
                clearTop = navCommand.clearTop
            )
            if (navCommand.finishCurrent) (context as? Activity)?.finish()
        }
        is NavigationCommand.StartIntent -> context.startActivity(navCommand.intent)
    }
}
