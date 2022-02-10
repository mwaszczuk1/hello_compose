package pl.mwaszczuk.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import pl.mwaszczuk.hellocompose.navigation.Navigator
import pl.mwaszczuk.hellocompose.ui.main.MainNavHostArchitected
import pl.mwaszczuk.hellocompose.ui.theme.HelloComposeTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivityArchitected : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HelloComposeTheme {
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = MaterialTheme.colors.isLight

                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.White,
                        darkIcons = useDarkIcons
                    )
                }
                MainNavHostArchitected(navigator)
            }
        }
    }

    override fun onBackPressed() {
        navigator.navigateBack()
    }
}


