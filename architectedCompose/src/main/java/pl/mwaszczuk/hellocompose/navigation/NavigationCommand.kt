package pl.mwaszczuk.hellocompose.navigation

import android.app.Activity
import android.content.Intent
import androidx.navigation.NavOptions

sealed class NavigationCommand {
    data class Navigate(
        val navAction: NavigationAction,
        val navRoute: String,
        val navOptions: NavOptions
    ) : NavigationCommand()

    data class PopUpTo(val route: String, val inclusive: Boolean) : NavigationCommand()
    data class StartActivity<T : Activity>(
        val activity: Class<out T>,
        val clearTop: Boolean,
        val finishCurrent: Boolean = false
    ) : NavigationCommand()

    class StartIntent(val intent: Intent) : NavigationCommand()
    object Back : NavigationCommand()
    data class DeepLink(val intent: Intent?) : NavigationCommand()
}
