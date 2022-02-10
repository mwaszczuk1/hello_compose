package pl.mwaszczuk.hellocompose.navigation.destinations

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.tween
import androidx.navigation.*
import pl.mwaszczuk.hellocompose.navigation.NavigationAction
import pl.mwaszczuk.hellocompose.navigation.NavigationGraph
import pl.mwaszczuk.hellocompose.ui.accountdetails.AccountDetailsScreenArchitected
import pl.mwaszczuk.hellocompose.ui.dashboard.DashboardScreenArchitected
import pl.mwaszczuk.hellocompose.raw.ui.main.Destination
import pl.mwaszczuk.hellocompose.ui.notifications.NotificationsScreenArchitected
import pl.mwaszczuk.hellocompose.ui.settings.SettingsScreenArchitected
import pl.mwaszczuk.hellocompose.util.EnterTransitionAnim
import pl.mwaszczuk.hellocompose.util.ExitTransitionAnim
import pl.mwaszczuk.hellocompose.util.mainScreenEnterTransition
import pl.mwaszczuk.hellocompose.util.mainScreenExitTransition

class MainNavGraph : NavigationGraph() {

    override val route: String = GRAPH_DESTINATION
    override val startDestination: String = DASHBOARD_DESTINATION

    fun attachToNavHost(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.createDestination(Dashboard()) {
            DashboardScreenArchitected()
        }
        navGraphBuilder.createDestination(Notifications()) {
            NotificationsScreenArchitected()
        }
        navGraphBuilder.createDestination(Settings()) {
            SettingsScreenArchitected()
        }
        navGraphBuilder.createDestination(AccountDetails()) {
            val name = it.arguments?.getString("name")
            val surname = it.arguments?.getString("surname")
            val accountType = it.arguments?.getString("accountType")
            AccountDetailsScreenArchitected(name, surname, accountType)
        }
    }

    class Dashboard : NavigationAction() {
        override val baseRoute: String
            get() = DASHBOARD_DESTINATION
        override val label: String
            get() = "Dashboard"
        override val navOptionsBuilder: NavOptions.Builder = NavOptions.Builder()
            .setPopUpTo(
                route = "MAIN_NAV_GRAPH_DESTINATION",
                inclusive = false
            )

        override val enterTransition: EnterTransitionAnim
            get() = { initial, target -> mainScreenEnterTransition(initial, target) }
        override val exitTransition: ExitTransitionAnim
            get() = { initial, target -> mainScreenExitTransition(initial, target) }
    }

    class Notifications : NavigationAction() {
        override val baseRoute: String
            get() = NOTIFICATIONS_DESTINATION
        override val label: String
            get() = "Notifications"
        override val navOptionsBuilder: NavOptions.Builder = NavOptions.Builder()
            .setPopUpTo(
                route = "MAIN_NAV_GRAPH_DESTINATION",
                inclusive = false
            )

        override val enterTransition: EnterTransitionAnim
            get() = { initial, target -> mainScreenEnterTransition(initial, target) }
        override val exitTransition: ExitTransitionAnim
            get() = { initial, target -> mainScreenExitTransition(initial, target) }
    }

    class Settings : NavigationAction() {
        override val baseRoute: String
            get() = SETTINGS_DESTINATION
        override val label: String
            get() = "Settings"
        override val navOptionsBuilder: NavOptions.Builder = NavOptions.Builder()
            .setPopUpTo(
                route = "MAIN_NAV_GRAPH_DESTINATION",
                inclusive = false
            )

        override val enterTransition: EnterTransitionAnim
            get() = { initial, target ->
                if (initial.destination.route == Destination.AccountDetails.route) {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Up,
                        animationSpec = tween(400)
                    )
                } else {
                    mainScreenEnterTransition(initial, target)
                }
            }
        override val exitTransition: ExitTransitionAnim
            get() = { initial, target ->
                if (target.destination.route == Destination.AccountDetails.route) {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Down,
                        animationSpec = tween(400)
                    )
                } else {
                    mainScreenExitTransition(initial, target)
                }
            }
    }

    class AccountDetails : NavigationAction() {
        override val baseRoute: String
            get() = ACCOUNT_DETAILS_DESTINATION
        override val label: String
            get() = "Account Details"

        override val arguments: List<NamedNavArgument> = listOf(
            navArgument(ARGUMENT_NAME) {
                type = NavType.StringType
            },
            navArgument(ARGUMENT_SURNAME) {
                type = NavType.StringType
                nullable = true
            },
            navArgument(ARGUMENT_ACCOUNT_TYPE) {
                type = NavType.StringType
                defaultValue = "User"
            }
        )

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

        companion object {
            const val ARGUMENT_NAME = "name"
            const val ARGUMENT_SURNAME = "surname"
            const val ARGUMENT_ACCOUNT_TYPE = "account_type"
        }
    }

    companion object {
        const val GRAPH_DESTINATION = "profile_nav_graph"

        const val DASHBOARD_DESTINATION = "dashboard"
        const val NOTIFICATIONS_DESTINATION = "notifications"
        const val SETTINGS_DESTINATION = "settings"
        const val ACCOUNT_DETAILS_DESTINATION = "account_details"
    }
}
