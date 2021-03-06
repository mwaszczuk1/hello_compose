package pl.mwaszczuk.hellocompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import pl.mwaszczuk.hellocompose.raw.ui.main.MainNavHost
import pl.mwaszczuk.hellocompose.ui.theme.HelloComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
                MainNavHost { startArchitectedActivity() }
            }
        }
    }

    private fun startArchitectedActivity() {
        val intent = Intent(this, MainActivityArchitected::class.java)
        startActivity(intent)
    }
}


