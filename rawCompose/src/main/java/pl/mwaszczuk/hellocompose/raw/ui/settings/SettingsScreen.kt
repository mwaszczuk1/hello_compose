package pl.mwaszczuk.hellocompose.raw.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pl.mwaszczuk.hellocompose.rawCompose.R
import pl.mwaszczuk.hellocompose.ui.theme.RedLight
import pl.mwaszczuk.hellocompose.ui.theme.White

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    navController: NavController
) {
    val userName = viewModel.userName
    val userSurname = viewModel.userSurname
    val accountType = viewModel.accountType
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                navController.navigate(
                    "ACCOUNT_DETAILS_DESTINATION/$userName?surname=$userSurname&accountType=$accountType"
                )
            },
            shape = RoundedCornerShape(24.dp),
            contentPadding = PaddingValues(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = RedLight,
                contentColor = White,
                disabledBackgroundColor = RedLight,
                disabledContentColor = White
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 8.dp
            ),
            border = null
        ) {
            Text(
                text = stringResource(R.string.show_account_details),
                color = White
            )
        }
    }
}
