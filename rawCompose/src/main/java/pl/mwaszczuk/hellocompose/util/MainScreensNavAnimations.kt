package pl.mwaszczuk.hellocompose.util

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import pl.mwaszczuk.hellocompose.raw.ui.main.Destination

val mainScreensMap = listOf(
    1 to Destination.Dashboard.route,
    2 to Destination.Profile.route,
    3 to Destination.Notifications.route,
    4 to Destination.Settings.route
)

fun AnimatedContentScope<String>.mainScreenEnterTransition(initial: NavBackStackEntry, target: NavBackStackEntry): EnterTransition {
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

fun AnimatedContentScope<String>.mainScreenExitTransition(initial: NavBackStackEntry, target: NavBackStackEntry): ExitTransition {
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
