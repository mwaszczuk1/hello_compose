package pl.mwaszczuk.hellocompose.raw.ui.showname

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AccountDetailsScreen(
    name: String,
    surname: String?,
    accountType: String
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "name arg: $name")
        Text(text = "surname arg: $surname")
        Text(text = "accountType arg: $accountType")
    }
}