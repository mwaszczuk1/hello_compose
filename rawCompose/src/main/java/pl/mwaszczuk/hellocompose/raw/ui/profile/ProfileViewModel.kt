package pl.mwaszczuk.hellocompose.raw.ui.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import pl.mwaszczuk.hellocompose.raw.ui.model.Achievement
import pl.mwaszczuk.hellocompose.rawCompose.R
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val achievementsMock = listOf(
        Achievement(
            name = "Achievement 1",
            description = "Lorem ipsum elo elo 320",
            icon = R.drawable.ic_achievement_svgrepo_com
        ),
        Achievement(
            name = "Achievement 2",
            description = "Lorem ipsum elo elo 320Lorem ipsum elo elo 320Lorem ipsum elo elo 320Lorem ipsum elo elo 320",
            icon = R.drawable.ic_achievement_svgrepo_com
        ),
        Achievement(
            name = "Achievement 3",
            description = "Lorem ipsum elo elo 320Lorem ipsum elo elo 320",
            icon = R.drawable.ic_achievement_svgrepo_com
        )
    )

    private val _achievements = MutableStateFlow(achievementsMock)
    val achievements = _achievements.asStateFlow()

    val userName = "Marek"
}
