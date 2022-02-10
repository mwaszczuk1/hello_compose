package pl.mwaszczuk.hellocompose.raw.ui.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModelArchitected @Inject constructor() : ViewModel() {
    val userName = "Marek"
    val userSurname = "Waszczuk"
    val accountType = "Admin"
}
