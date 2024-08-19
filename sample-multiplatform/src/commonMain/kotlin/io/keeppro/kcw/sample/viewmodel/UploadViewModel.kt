package io.keeppro.kcw.sample.viewmodel

import io.keeppro.kcw.core.KCWClient
import io.keeppro.kcw.core.KCWConfig
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.getString
import tim.huang.kcw.Res
import tim.huang.kcw.base_url

@OptIn(ExperimentalResourceApi::class)
class UploadViewModel(
    private val coroutineScope: CoroutineScope,
) {
    val state = MutableStateFlow<UploadState>(UploadState.Idle)

    val exceptionHandler = CoroutineExceptionHandler{_, throwable ->
        state.value = UploadState.Error(throwable.message ?: "Unknown error")
    }

    fun upload() {
        state.value = UploadState.Uploading

        coroutineScope.launch(exceptionHandler) {
            //request to worker through KCWClient
            val kcwClient: KCWClient = KCWClient.create(
                KCWConfig(baseUrl = getString(Res.string.base_url))
            )
            kcwClient.uploadFile(
                path = "projects/test123/logo",
                contentType = "image/jpeg",
                byteArray = Res.readBytes("files/display_pad.jpeg")
            )
            state.value = UploadState.Success
        }

    }

}

sealed interface UploadState {
    data object Idle : UploadState
    data object Uploading : UploadState
    data object Success : UploadState
    data class Error(val message: String) : UploadState
}