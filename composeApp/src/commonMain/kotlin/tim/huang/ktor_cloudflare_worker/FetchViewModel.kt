package tim.huang.ktor_cloudflare_worker

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
            state.value = FetchState.Success("https://upload-files-toy-worker.t8522192.workers.dev/projects/test123/logo")
        }
    }
}

sealed interface FetchState {
    data object Idle : FetchState
    data object Fetching : FetchState
    data class Success(val url: String): FetchState
}
