package io.keeppro.kcw.core

import io.keeppro.kcw.exceptions.KCWException
import io.keeppro.kcw.http.defaultHttpClient
import io.keeppro.kcw.utils.UrlBuilder
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsChannel
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.content.ByteArrayContent
import io.ktor.http.isSuccess
import io.ktor.util.toByteArray
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class KCWClientImpl(private val config: io.keeppro.kcw.core.KCWConfig) : io.keeppro.kcw.core.KCWClient {
    val client = defaultHttpClient
    private val json = Json { ignoreUnknownKeys = true }

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

    override suspend fun getImage(blobUrl: String): ByteArray {
        val response: HttpResponse = client.get(blobUrl) {
            accept(ContentType.Image.Any)
        }

        if (response.status.isSuccess()) {
            return response.bodyAsChannel().toByteArray()
        } else {
            throw KCWException("Failed to download image. Status: ${response.status}")
        }
    }

    suspend inline fun <reified T> get(url: String): T {
        val response: HttpResponse = client.get(url)
        return response.body()
    }

    suspend inline fun <reified T : Any, reified R> post(url: String, body: T): R {
        val response: HttpResponse = client.post(url) {
            setBody(Json.encodeToString(body))
        }
        return response.body()
    }

    suspend inline fun <reified T, reified R> put(url: String, body: T): R {
        val response: HttpResponse = client.put(url) {
            setBody(Json.encodeToString(body))
        }
        return response.body()
    }

    suspend inline fun <reified R> delete(url: String): R {
        val response: HttpResponse = client.delete(url)
        return response.body()
    }

    fun close() {
        client.close()
    }

}