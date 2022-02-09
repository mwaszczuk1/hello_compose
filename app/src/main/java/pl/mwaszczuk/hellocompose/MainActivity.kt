package pl.mwaszczuk.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import pl.mwaszczuk.hellocompose.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloComposeTheme() {
                // A surface container using the 'background' color from the theme

                Scaffold {
                    MainNavHost()
                }
            }
        }
    }
}

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = "start"
    ) {
        this.navigation(
            startDestination = "MAIN_SCREEN",
            route = "MAIN_NAV_GRAPH"
        ) {
            this.composable(
                route = "MAIN_SCREEN",
                arguments = emptyList(),
                deepLinks = emptyList()
            ) {

            }
        }
    }
}
