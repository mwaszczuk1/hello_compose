package pl.mwaszczuk.hellocompose.raw.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pl.mwaszczuk.hellocompose.raw.ui.main.Destination
import pl.mwaszczuk.hellocompose.rawCompose.R
import pl.mwaszczuk.hellocompose.ui.theme.RedLight
import pl.mwaszczuk.hellocompose.ui.theme.White

@Composable
fun ProfileScreenArchitected(
    viewModel: ProfileViewModel,
    navController: NavController
) {

    val userName = remember { viewModel.userName }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 16.dp),
            text = stringResource(R.string.hello_user, userName),
            fontSize = 24.sp
        )

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            onClick = { navController.navigate(Destination.Achievements.route) },
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
                text = stringResource(R.string.achievements_screen_title),
                color = White
            )
        }
    }
}
