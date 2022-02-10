package pl.mwaszczuk.hellocompose.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import pl.mwaszczuk.hellocompose.navigation.NavigationAction
import pl.mwaszczuk.hellocompose.navigation.destinations.MainNavGraph
import pl.mwaszczuk.hellocompose.navigation.destinations.ProfileNavGraph
import pl.mwaszczuk.hellocompose.rawCompose.R

sealed class BottomNavigationItemArchitected(
    val destination: NavigationAction,
    @StringRes val name: Int,
    @DrawableRes private val iconFilled: Int,
    @DrawableRes private val iconOutlined: Int
) {

    fun getIcon(currentDestinationRoute: String?) = if (destination.baseRoute == currentDestinationRoute) {
        iconFilled
    } else {
        iconOutlined
    }

    object Dashboard : BottomNavigationItemArchitected(
        MainNavGraph.Dashboard(),
        R.string.dashboard_screen_title,
        R.drawable.ic_baseline_dashboard_24,
        R.drawable.ic_outline_dashboard_24
    )

    object Profile : BottomNavigationItemArchitected(
        ProfileNavGraph.Profile(),
        R.string.profile_screen_title,
        R.drawable.ic_baseline_person_24,
        R.drawable.ic_baseline_person_outline_24
    )

    object Notifications : BottomNavigationItemArchitected(
        MainNavGraph.Notifications(),
        R.string.notifications_screen_title,
        R.drawable.ic_baseline_notifications_24,
        R.drawable.ic_baseline_notifications_outline_24
    )

    object Settings : BottomNavigationItemArchitected(
        MainNavGraph.Settings(),
        R.string.settings_screen_title,
        R.drawable.ic_baseline_settings_24,
        R.drawable.ic_outline_settings_24
    )
}
