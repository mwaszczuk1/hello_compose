package pl.mwaszczuk.hellocompose.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.get
import com.google.accompanist.navigation.animation.AnimatedComposeNavigator

@OptIn(ExperimentalStdlibApi::class)
@ExperimentalAnimationApi
fun NavGraphBuilder.composable(
    destination: NavigationAction,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
    deepLinks: List<NavDeepLink> = emptyList()
) {
    addDestination(
        AnimatedComposeNavigator.Destination(
            provider[AnimatedComposeNavigator::class],
            content,
            destination.enterTransition,
            destination.exitTransition,
            destination.popEnterTransition,
            destination.popExitTransition
        ).apply {
            this.route = destination.route
            this.label = destination.label
            buildList {
                addAll(destination.requiredArguments)
                addAll(destination.optionalArguments)
            }.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}
