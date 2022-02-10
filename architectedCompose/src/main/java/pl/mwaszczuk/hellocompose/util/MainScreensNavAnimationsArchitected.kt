package pl.mwaszczuk.hellocompose.util

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import pl.mwaszczuk.hellocompose.navigation.destinations.MainNavGraph
import pl.mwaszczuk.hellocompose.navigation.destinations.ProfileNavGraph

val mainScreensMap = listOf(
    1 to MainNavGraph.DASHBOARD_DESTINATION,
    2 to ProfileNavGraph.PROFILE_DESTINATION,
    3 to MainNavGraph.NOTIFICATIONS_DESTINATION,
    4 to MainNavGraph.SETTINGS_DESTINATION
)

fun AnimatedContentScope<String>.mainScreenEnterTransitionArchitected(initial: NavBackStackEntry, target: NavBackStackEntry): EnterTransition {
    val initialIndex = mainScreensMap.firstOrNull {
        initial.destination.route == it.second
    }?.first
    val targetIndex = mainScreensMap.firstOrNull {
        target.destination.route == it.second
    }?.first
    return if (initialIndex != null && targetIndex != null) {
        when {
            initialIndex > targetIndex -> slideIntoContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(400)
            )
            initialIndex < targetIndex -> slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(400)
            )
            else -> fadeIn(animationSpec = tween(400))
        }
    } else {
        fadeIn(animationSpec = tween(400))
    }
}

fun AnimatedContentScope<String>.mainScreenExitTransitionArchitected(initial: NavBackStackEntry, target: NavBackStackEntry): ExitTransition {
    val initialIndex = mainScreensMap.firstOrNull {
        initial.destination.route?.contains(it.second) == true
    }?.first
    val targetIndex = mainScreensMap.firstOrNull {
        target.destination.route?.contains(it.second) == true
    }?.first
    return if (initialIndex != null && targetIndex != null) {
        when {
            initialIndex > targetIndex -> slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(400)
            )
            initialIndex < targetIndex -> slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(400)
            )
            else -> fadeOut(animationSpec = tween(400))
        }
    } else {
        fadeOut(animationSpec = tween(400))
    }
}

