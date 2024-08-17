package tim.huang.kcw.sample.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FetchViewModel(
    private val coroutineScope: CoroutineScope
) {

    val state = MutableStateFlow<FetchState>(FetchState.Idle)

    fun fetch() {
        state.value = FetchState.Fetching
        coroutineScope.launch {
            //here we pass url to AsyncImage to load from its own
            //but can fetch the image url from worker as well, see KCWClient.getImage
            state.value =
                FetchState.Success("https://upload-files-toy-worker.t8522192.workers.dev/projects/test123/logo")
        }
    }
}

sealed interface FetchState {
    data object Idle : FetchState
    data object Fetching : FetchState
    data class Success(val url: String): FetchState
}
