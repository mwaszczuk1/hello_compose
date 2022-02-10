package pl.mwaszczuk.hellocompose.ui.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.mwaszczuk.hellocompose.navigation.NavigationAction
import pl.mwaszczuk.hellocompose.navigation.Navigator
import pl.mwaszczuk.hellocompose.navigation.destinations.MainNavGraph
import javax.inject.Inject

@HiltViewModel
class SettingsViewModelArchitected @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {
    private val userName = "Marek"
    private val userSurname = "Waszczuk"
    private val accountType = "Admin"

    fun navigateToAccountDetails() {
        navigator.navigateTo(
            MainNavGraph.AccountDetails(),
            NavigationAction.Argument(
                MainNavGraph.AccountDetails.ARGUMENT_NAME,
                userName
            ),
            NavigationAction.Argument(
                MainNavGraph.AccountDetails.ARGUMENT_SURNAME,
                userSurname
            ),
            NavigationAction.Argument(
                MainNavGraph.AccountDetails.ARGUMENT_ACCOUNT_TYPE,
                accountType
            )
        )
    }
}
