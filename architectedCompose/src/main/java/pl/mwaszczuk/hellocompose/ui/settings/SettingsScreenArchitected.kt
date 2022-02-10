package pl.mwaszczuk.hellocompose.ui.settings

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
import pl.mwaszczuk.hellocompose.rawCompose.R
import pl.mwaszczuk.hellocompose.ui.settings.SettingsViewModelArchitected
import pl.mwaszczuk.hellocompose.ui.theme.RedLight
import pl.mwaszczuk.hellocompose.ui.theme.White

@Composable
fun SettingsScreenArchitected(
    viewModel: SettingsViewModelArchitected = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { viewModel.navigateToAccountDetails() },
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
