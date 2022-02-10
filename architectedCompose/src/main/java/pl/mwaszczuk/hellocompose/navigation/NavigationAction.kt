package pl.mwaszczuk.hellocompose.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import pl.mwaszczuk.hellocompose.util.EnterTransitionAnim
import pl.mwaszczuk.hellocompose.util.ExitTransitionAnim
import pl.mwaszczuk.hellocompose.util.PopEnterTransitionAnim
import pl.mwaszczuk.hellocompose.util.PopExitTransitionAnim

@Suppress("SpreadOperator")
abstract class NavigationAction {

    /**
     * Base destination route (without arguments). For example "reenter_pin_destination"
     */
    abstract val baseRoute: String

    /**
     * Destination route including path arguments. For example "reenter_pin_destination/{pin_argument}"
     * It is used when the destination is being initialized in the nav graph
     */
    val route: String
        get() = baseRoute.withPathArgs(requiredArguments, optionalArguments)

    /**
     * Screen label that is going to be displayed in a top bar by default.
     */
    abstract val label: String

    open val navOptionsBuilder = NavOptions.Builder().setLaunchSingleTop(true)
    open val navigatorExtras: Navigator.Extras? = null
    protected open val arguments: List<NamedNavArgument> = listOf()

    val requiredArguments: List<NamedNavArgument>
        get() = arguments.filter { !it.argument.isDefaultValuePresent && !it.argument.isNullable }
    val optionalArguments: List<NamedNavArgument>
        get() = arguments.filter { it.argument.isDefaultValuePresent || it.argument.isNullable }

    /**
     * Navigation Animations for this screen.
     * Override those if there is non-default pl.mwaszczuk.hellocompose.navigation animation needed for a destination.
     */
    open val enterTransition: EnterTransitionAnim = null
    open val exitTransition: ExitTransitionAnim = null
    open val popEnterTransition: PopEnterTransitionAnim = null
    open val popExitTransition: PopExitTransitionAnim = null

    private fun String.withPathArgs(
        requiredArgs: List<NamedNavArgument>,
        optionalArgs: List<NamedNavArgument>
    ) = buildString {
        append(this@withPathArgs)
        requiredArgs.forEach { arg ->
            append("/{${arg.name}}")
        }
        optionalArgs.forEachIndexed { index, arg ->
            val separationSign = if (index == 0) {
                "?"
            } else {
                "&"
            }
            append("$separationSign${arg.name}={${arg.name}}")
        }
    }


    class Argument(
        val name: String,
        val argString: String
    )
}
