package tim.huang.kcw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            tim.huang.kcw.sample.App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    tim.huang.kcw.sample.App()
}