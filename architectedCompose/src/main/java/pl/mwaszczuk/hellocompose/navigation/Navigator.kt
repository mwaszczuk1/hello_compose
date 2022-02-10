package pl.mwaszczuk.hellocompose.navigation

import android.app.Activity
import android.content.Intent
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.Flow
import pl.mwaszczuk.hellocompose.mock.AnalyticsLogger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor(
    private val analyticsLogger: AnalyticsLogger,
    private val navigationManager: NavigationManager
) {

    val navigationCommands: Flow<NavigationCommand> = navigationManager.navActions

    fun navigateTo(
        navAction: NavigationAction,
        vararg arguments: NavigationAction.Argument,
        navOptionsBuilder: (builder: NavOptions.Builder) -> NavOptions.Builder = { it }
    ) {
        val navRoute = navAction.getBaseRouteWithArgs(arguments.toList())
        navigationManager.navigate(
            NavigationCommand.Navigate(
                navAction,
                navRoute,
                navOptionsBuilder(navAction.navOptionsBuilder).build()
            )
        )
    }

    fun navigateBack() {
        navigationManager.navigate(NavigationCommand.Back)
    }

    fun popUpTo(route: String, inclusive: Boolean) {
        navigationManager.navigate(NavigationCommand.PopUpTo(route, inclusive))
    }

    fun onNavigationChanged(navigationAction: NavigationAction) {
        analyticsLogger.log(navigationAction.baseRoute)
    }

    fun execute(navigationCommand: NavigationCommand) {
        navigationManager.navigate(navigationCommand)
    }

    fun <T : Activity> startActivity(
        activity: Class<out T>,
        clearTop: Boolean = false,
        finishCurrent: Boolean = false
    ) {
        navigationManager.navigate(
            NavigationCommand.StartActivity(activity, clearTop, finishCurrent)
        )
    }

    fun startIntent(
        action: String,
        category: String? = null,
        params: Map<String, String> = emptyMap()
    ) {
        val intent = Intent.makeMainSelectorActivity(
            action,
            category
        ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        params.forEach {
            intent.putExtra(it.key, it.value)
        }

        navigationManager.navigate(
            NavigationCommand.StartIntent(intent)
        )
    }

    fun NavigationAction.popUpTo(
        route: String,
        inclusive: Boolean = false
    ): NavigationAction {
        navOptionsBuilder.setPopUpTo(
            route = route,
            inclusive = inclusive
        )
        return this
    }

    private fun NavigationAction.getBaseRouteWithArgs(
        args: List<NavigationAction.Argument>
    ): String {
        val missingArg = requiredArguments.firstOrNull { !args.map { it.name }.contains(it.name) }
        if (missingArg != null) {
            throw IllegalStateException("Missing required argument {${missingArg.name}} for destination: ${this.route}")
        }
        val requiredArgs = args.filter { requiredArguments.map { it.name }.contains(it.name) }
        val optionalArgs = args.filter { optionalArguments.map { it.name }.contains(it.name) }
        return buildString {
            append(this@getBaseRouteWithArgs.baseRoute)
            requiredArgs.forEach { arg ->
                val namedArg = requiredArguments.firstOrNull { it.name == arg.name }
                if (namedArg != null) {
                    append("/${arg.argString}")
                }
            }
            optionalArgs.forEachIndexed { index, arg ->
                val namedNavArg = optionalArguments.firstOrNull { it.name == arg.name }
                if (namedNavArg != null) {
                    val separationSign = if (index == 0) {
                        "?"
                    } else {
                        "&"
                    }
                    append("$separationSign${arg.name}=${arg.argString}")
                }
            }
        }
    }
}
