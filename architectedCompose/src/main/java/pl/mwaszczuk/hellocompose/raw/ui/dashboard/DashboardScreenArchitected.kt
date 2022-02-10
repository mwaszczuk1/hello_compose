package pl.mwaszczuk.hellocompose.raw.ui.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mwaszczuk.hellocompose.rawCompose.R
import pl.mwaszczuk.hellocompose.ui.theme.RedLight

@Composable
fun DashboardScreenArchitected() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Icon(
                modifier = Modifier.requiredSize(72.dp).align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.ic_baseline_dashboard_24),
                contentDescription = "dashboard_icon",
                tint = RedLight
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.dashboard_screen_title),
                color = RedLight,
                fontSize = 20.sp
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    DashboardScreen()
}
