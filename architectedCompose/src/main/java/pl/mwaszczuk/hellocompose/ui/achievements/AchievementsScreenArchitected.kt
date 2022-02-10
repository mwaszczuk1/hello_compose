package pl.mwaszczuk.hellocompose.ui.achievements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.mwaszczuk.hellocompose.raw.ui.model.Achievement
import pl.mwaszczuk.hellocompose.ui.profile.ProfileViewModelArchitected
import pl.mwaszczuk.hellocompose.ui.theme.Gray

@Composable
fun AchievementsScreenArchitected(
    viewModel: ProfileViewModelArchitected
) {
    val achievements by viewModel.achievements.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(achievements) { item ->
            AchievementItem(item)
        }
    }
}

@Composable
fun AchievementItem(item: Achievement) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(BorderStroke(2.dp, Gray), RoundedCornerShape(24.dp))
    ) {
        Image(
            modifier = Modifier
                .requiredSize(60.dp)
                .padding(start = 16.dp, end = 8.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(item.icon),
            contentDescription = "achievement_icon"
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp, horizontal = 8.dp)
        ) {
            Text(
                modifier = Modifier,
                text = item.name,
                fontSize = 16.sp
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = item.description,
                fontSize = 12.sp
            )
        }
    }
}
