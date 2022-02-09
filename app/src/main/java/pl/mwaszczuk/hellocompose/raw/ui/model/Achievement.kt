package pl.mwaszczuk.hellocompose.raw.ui.model

import androidx.annotation.DrawableRes

data class Achievement(
    val name: String,
    val description: String,
    @DrawableRes val icon: Int
)
