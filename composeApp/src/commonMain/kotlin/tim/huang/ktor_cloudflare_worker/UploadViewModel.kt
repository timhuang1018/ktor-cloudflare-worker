package tim.huang.ktor_cloudflare_worker

import io.ktor.client.request.header
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.content.ByteArrayContent
import io.ktor.http.ContentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ktor_cloudflare_worker.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi
import tim.huang.ktor_cloudflare_worker.exceptions.KCWException
import tim.huang.ktor_cloudflare_worker.http.defaultHttpClient
import tim.huang.ktor_cloudflare_worker.utils.UrlBuilder

@OptIn(ExperimentalResourceApi::class)
class UploadViewModel(
    private val coroutineScope: CoroutineScope
) {
    val state = MutableStateFlow<UploadState>(UploadState.Idle)

    val exceptionHandler = CoroutineExceptionHandler{_, throwable ->
        state.value = UploadState.Error(throwable.message ?: "Unknown error")
    }


    fun upload() {
        state.value = UploadState.Uploading

        coroutineScope.launch(exceptionHandler) {
            //request to worker through KCWClient
            uploadFile(
                path = "projects/test123/logo",
                contentType = "image/jpeg",
                byteArray = Res.readBytes("files/display_pad.jpeg")
            )
            state.value = UploadState.Success
        }

    }

    suspend fun uploadFile(path: String, byteArray: ByteArray, contentType: String, headers: Map<String, String> = emptyMap()): String {

        val response: HttpResponse = defaultHttpClient.put(UrlBuilder.buildUrl("https://upload-files-toy-worker.t8522192.workers.dev", path)) {
            headers.forEach { (key, value) -> header(key, value) }

            setBody(
                ByteArrayContent(
                    bytes = byteArray,
                    contentType = contentType.let { ContentType.parse(it) }
                )
            )
        }

        if (response.status.isSuccess()) {
            return response.bodyAsText()
        } else {
            throw KCWException("Failed to upload file. Status: ${response.status}")
        }
    }
}

sealed interface UploadState {
    data object Idle : UploadState
    data object Uploading : UploadState
    data object Success : UploadState
    data class Error(val message: String) : UploadState
}