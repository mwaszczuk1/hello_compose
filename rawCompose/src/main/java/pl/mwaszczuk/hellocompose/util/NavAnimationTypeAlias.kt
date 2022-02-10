package pl.mwaszczuk.hellocompose.util

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry

typealias EnterTransitionAnim = (
AnimatedContentScope<String>.(
    initial: NavBackStackEntry,
    target: NavBackStackEntry
) -> EnterTransition?
)?

typealias ExitTransitionAnim = (
AnimatedContentScope<String>.(
    initial: NavBackStackEntry,
    target: NavBackStackEntry
) -> ExitTransition?
)?

typealias PopEnterTransitionAnim = (
AnimatedContentScope<String>.(
    initial: NavBackStackEntry,
    target: NavBackStackEntry
) -> EnterTransition?
)?

typealias PopExitTransitionAnim = (
AnimatedContentScope<String>.(
    initial: NavBackStackEntry,
    target: NavBackStackEntry
) -> ExitTransition?
)?
