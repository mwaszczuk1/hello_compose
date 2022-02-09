package pl.mwaszczuk.hellocompose.raw.ui.settings

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import pl.mwaszczuk.hellocompose.R
import pl.mwaszczuk.hellocompose.raw.ui.model.Achievement
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {
    val userName = "Marek"
    val userSurname = "Waszczuk"
    val accountType = "Admin"
}
