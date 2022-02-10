package pl.mwaszczuk.hellocompose.ui.main

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pl.mwaszczuk.hellocompose.mock.AnalyticsLogger
import pl.mwaszczuk.hellocompose.navigation.NavigationManagerImpl
import pl.mwaszczuk.hellocompose.navigation.Navigator
import pl.mwaszczuk.hellocompose.raw.ui.main.BottomNavigationItem
import pl.mwaszczuk.hellocompose.ui.theme.RedLight

/**
 * MainBottomBar Composable of the app.
 */
@Composable
fun MainBottomBarArchitected(
    navigator: Navigator,
    currentRoute: String?
) {
    MainBottomNavController(
        navigator = navigator,
        currentRoute = currentRoute,
        items = listOf(
            BottomNavigationItemArchitected.Dashboard,
            BottomNavigationItemArchitected.Profile,
            BottomNavigationItemArchitected.Notifications,
            BottomNavigationItemArchitected.Settings
        )
    )
}

/**
 * Bottombar layout
 * @param items Bottom Bar menu items to be displayed
 * and handles tutorial targeted Composables measurements
 */
@Composable
fun MainBottomNavController(
    navigator: Navigator,
    currentRoute: String?,
    items: List<BottomNavigationItemArchitected>
) {
    CustomBottomNavigation(
        modifier = Modifier.requiredHeight(56.dp),
        backgroundColor = White,
        elevation = 8.dp,
    ) {
        items.forEachIndexed { index, screen ->
            val currentIcon = screen.getIcon(currentRoute)
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .selectable(
                        selected = currentRoute == screen.destination.route,
                        onClick = { navigator.navigateTo(screen.destination) },
                        role = Role.Tab
                    )
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                androidx.compose.animation.AnimatedVisibility(
                    visible = currentRoute == screen.destination.baseRoute
                ) {
                    Divider(
                        modifier = Modifier
                            .requiredWidth(64.dp)
                            .clip(
                                RoundedCornerShape(
                                    bottomStart = 2.dp,
                                    bottomEnd = 2.dp
                                )
                            ),
                        color = RedLight,
                        thickness = 4.dp
                    )
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    val color = animateColorAsState(
                        targetValue = if (currentRoute == screen.destination.baseRoute) {
                            RedLight
                        } else {
                            Gray
                        },
                        animationSpec = spring()
                    )
                    Box(
                        modifier = Modifier.requiredSize(24.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Crossfade(targetState = currentIcon, animationSpec = spring()) { icon ->
                            Icon(
                                painter = painterResource(icon),
                                contentDescription = stringResource(screen.name),
                                tint = color.value,
                            )
                        }
                    }
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = screen.name),
                        maxLines = 1,
                        color = color.value,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun CustomBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = BottomNavigationDefaults.Elevation,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        modifier = modifier
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}

@Preview
@Composable
fun MainBottomNavControllerPreview() {
    MainBottomNavController(
        navigator = Navigator(AnalyticsLogger(), NavigationManagerImpl()),
        currentRoute = "",
        items = listOf(
            BottomNavigationItemArchitected.Dashboard,
            BottomNavigationItemArchitected.Profile,
            BottomNavigationItemArchitected.Notifications,
            BottomNavigationItemArchitected.Settings
        )
    )
}
