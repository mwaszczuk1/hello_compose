package pl.mwaszczuk.hellocompose.raw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import pl.mwaszczuk.hellocompose.raw.ui.main.MainNavHost
import pl.mwaszczuk.hellocompose.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.setBackgroundColor(-0x1)

        setContent {
            HelloComposeTheme {
                MainNavHost()
            }
        }
    }
}


