package pl.mwaszczuk.hellocompose.navigation.destinations

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.*
import com.google.accompanist.navigation.animation.navigation
import pl.mwaszczuk.hellocompose.navigation.NavigationAction
import pl.mwaszczuk.hellocompose.navigation.NavigationGraph
import pl.mwaszczuk.hellocompose.ui.achievements.AchievementsScreenArchitected
import pl.mwaszczuk.hellocompose.raw.ui.main.Destination
import pl.mwaszczuk.hellocompose.ui.profile.ProfileScreenArchitected
import pl.mwaszczuk.hellocompose.util.*

class ProfileNavGraph(private val navController: NavController) : NavigationGraph() {

    override val route: String = GRAPH_DESTINATION
    override val startDestination: String = PROFILE_DESTINATION

    fun attachToNavHost(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.navigation(
            startDestination = startDestination,
            route = GRAPH_DESTINATION,
            enterTransition = { _, _ -> fadeIn(animationSpec = tween(400)) },
            exitTransition = { _, _ -> fadeOut(animationSpec = tween(400)) },
            popEnterTransition = { _, _ -> fadeIn(animationSpec = tween(400)) },
            popExitTransition = { _, _ -> fadeOut(animationSpec = tween(400)) }
        ) {
            createDestination(Profile()) {
                ProfileScreenArchitected(parentViewModel(it, navController))
            }
            createDestination(Achievements()) {
                AchievementsScreenArchitected(parentViewModel(it, navController))
            }
        }
    }

    class Profile : NavigationAction() {
        override val baseRoute: String
            get() = PROFILE_DESTINATION
        override val label: String
            get() = "Profile"
        override val navOptionsBuilder: NavOptions.Builder = NavOptions.Builder()
            .setPopUpTo(
                route = "MAIN_NAV_GRAPH_DESTINATION",
                inclusive = false
            )

        override val enterTransition: EnterTransitionAnim
            get() = { initial, target ->
                if (initial.destination.route == ACHIEVEMENTS_DESTINATION) {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(400)
                    )
                } else {
                    mainScreenEnterTransitionArchitected(initial, target)
                }
            }
        override val exitTransition: ExitTransitionAnim
            get() = { initial, target ->
                if (target.destination.route == ACHIEVEMENTS_DESTINATION) {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Down,
                        animationSpec = tween(400)
                    )
                } else {
                    mainScreenExitTransitionArchitected(initial, target)
                }
            }
    }

    class Achievements : NavigationAction() {
        override val baseRoute: String
            get() = ACHIEVEMENTS_DESTINATION
        override val label: String
            get() = "Achievements"

        override val enterTransition: EnterTransitionAnim
            get() = { _, _ ->
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Down,
                    animationSpec = tween(400)
                )
            }
        override val exitTransition: ExitTransitionAnim
            get() = { _, _ ->
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Up,
                    animationSpec = tween(400)
                )
            }
        override val popEnterTransition: PopEnterTransitionAnim
            get() = { _, _ ->
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Down,
                    animationSpec = tween(400)
                )
            }
        override val popExitTransition: PopExitTransitionAnim
            get() = { _, _ ->
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Up,
                    animationSpec = tween(400)
                )
            }
    }

    companion object {
        const val GRAPH_DESTINATION = "profile_nav_graph"

        const val PROFILE_DESTINATION = "profile"
        const val ACHIEVEMENTS_DESTINATION = "achievements"
    }
}
