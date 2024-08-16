package tim.huang.ktor_cloudflare_worker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.core.context.loadKoinModules

@Composable
@Preview
fun App() {
    MaterialTheme {

        KoinApplication(application = {
            loadKoinModules(shareModule)
        }){
            DemoApp()
        }
    }
}

@Composable
fun DemoApp() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        ) {

        UploadSession()
        FetchSession()
    }

}

@Composable
fun UploadSession(uploadViewModel: UploadViewModel = koinInject()) {
    Surface(modifier = Modifier.padding(16.dp)) {

        val stateChanged by uploadViewModel.state.collectAsState()

        when(val state = stateChanged){
            UploadState.Uploading -> {
                CircularProgressIndicator()
            }
            UploadState.Success -> {
                Text("Upload success")
            }
            is UploadState.Error -> {
                Text(state.message)
            }
            else -> {
                Button(onClick = uploadViewModel::upload) {
                    Text("Upload image")
                }
            }
        }

    }
}

@Composable
fun FetchSession() {
    Surface(modifier = Modifier.padding(16.dp)) {


        Button(onClick = { /*TODO*/ }) {
            Text("Fetch image")
        }
        //        AsyncImage(
//            model = "https://example.com/image.jpg",
//            contentDescription = null,
//        )
    }
}
