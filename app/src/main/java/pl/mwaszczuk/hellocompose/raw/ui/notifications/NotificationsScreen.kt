package pl.mwaszczuk.hellocompose.raw.ui.notifications

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pl.mwaszczuk.hellocompose.raw.ui.dashboard.DashboardScreen

@Composable
fun NotificationsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "NO ELO NOTIFICATIONS")
    }
}

@Preview
@Composable
fun NotificationsScreenPreview() {
    DashboardScreen()
}
