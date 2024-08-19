package io.keeppro.kcw.core

import io.keeppro.kcw.http.defaultHttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


interface KCWClient {

    suspend fun uploadFile(path: String, byteArray: ByteArray, contentType: String, headers: Map<String, String> = emptyMap()): String
    suspend fun getImage(blobUrl: String): ByteArray

    companion object {
        val client = defaultHttpClient

        fun create(config: KCWConfig): KCWClient = KCWClientImpl(config)

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
}