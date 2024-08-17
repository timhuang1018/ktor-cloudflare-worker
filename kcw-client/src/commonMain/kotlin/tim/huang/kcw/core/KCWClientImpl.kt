package tim.huang.kcw.core

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.ByteArrayContent
import kotlinx.serialization.json.Json
import tim.huang.kcw.exceptions.KCWException
import tim.huang.kcw.http.defaultHttpClient
import tim.huang.kcw.utils.UrlBuilder
import kotlin.reflect.KClass

class KCWClientImpl(private val config: KCWConfig) : KCWClient {
    private val client = defaultHttpClient
    private val json = Json { ignoreUnknownKeys = true }

//    override suspend fun <T> get(path: String, headers: Map<String, String>): T {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun <T> post(path: String, body: Any, headers: Map<String, String>): T = request(HttpMethod.Post, path, body, headers)
//
//    override suspend fun <T> put(path: String, body: Any, headers: Map<String, String>): T = request(HttpMethod.Put, path, body, headers)
//
//    override suspend fun <T> delete(path: String, headers: Map<String, String>): T = request(HttpMethod.Delete, path, null, headers)
//
//    suspend inline fun <reified T> request(method: HttpMethod, path: String, body: Any?, headers: Map<String, String>): T {
//        val response: HttpResponse = client.request(UrlBuilder.buildUrl(config.baseUrl, path)) {
//            this.method = method
//            headers.forEach { (key, value) -> header(key, value) }
//            if (body != null) {
//                contentType(ContentType.Application.Json)
//                setBody(json.encodeToString(body))
//            }
//        }
//
//        val responseBody = response.bodyAsText()
//        return when (T::class) {
//            String::class -> responseBody as T
//            else -> json.decodeFromString(responseBody)
//        }
//    }

    override suspend fun uploadFile(path: String, byteArray: ByteArray, contentType: String, headers: Map<String, String>): String {

        val response: HttpResponse = client.put(UrlBuilder.buildUrl(config.baseUrl, path)) {
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