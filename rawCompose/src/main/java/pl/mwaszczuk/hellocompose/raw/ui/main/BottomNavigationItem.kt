package pl.mwaszczuk.hellocompose.raw.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import pl.mwaszczuk.hellocompose.rawCompose.R

sealed class BottomNavigationItem(
    val destinationRoute: String,
    @StringRes val name: Int,
    @DrawableRes private val iconFilled: Int,
    @DrawableRes private val iconOutlined: Int
) {

    fun getIcon(currentDestinationRoute: String?) = if (destinationRoute == currentDestinationRoute) {
        iconFilled
    } else {
        iconOutlined
    }

    object Dashboard : BottomNavigationItem(
        "DASHBOARD_DESTINATION",
        R.string.dashboard_screen_title,
        R.drawable.ic_baseline_dashboard_24,
        R.drawable.ic_outline_dashboard_24
    )

    object Profile : BottomNavigationItem(
        "PROFILE_DESTINATION",
        R.string.profile_screen_title,
        R.drawable.ic_baseline_person_24,
        R.drawable.ic_baseline_person_outline_24
    )

    object Notifications : BottomNavigationItem(
        "NOTIFICATIONS_DESTINATION",
        R.string.notifications_screen_title,
        R.drawable.ic_baseline_notifications_24,
        R.drawable.ic_baseline_notifications_outline_24
    )

    object Settings : BottomNavigationItem(
        "SETTINGS_DESTINATION",
        R.string.settings_screen_title,
        R.drawable.ic_baseline_settings_24,
        R.drawable.ic_outline_settings_24
    )
}
