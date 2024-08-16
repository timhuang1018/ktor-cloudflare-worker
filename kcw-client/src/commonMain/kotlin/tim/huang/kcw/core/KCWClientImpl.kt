//package tim.huang.ktor_cloudflare_worker.core
//
//import io.ktor.client.*
//import io.ktor.client.engine.cio.*
//import io.ktor.client.request.*
//import io.ktor.client.statement.*
//import io.ktor.http.*
//import io.ktor.http.ContentType.Application.Json
//import kotlinx.serialization.json.Json
//
//class KCWClientImpl(private val config: KCWConfig) : KCWClient {
//    private val client = HttpClient(CIO) {
//        engine {
//            requestTimeout = config.timeout
//        }
//    }
//
//    private val json = Json { ignoreUnknownKeys = true }
//
//    override suspend fun <T> get(path: String, headers: Map<String, String>): T = request(HttpMethod.Get, path, null, headers)
//
//    override suspend fun <T> post(path: String, body: Any, headers: Map<String, String>): T = request(HttpMethod.Post, path, body, headers)
//2
//    override suspend fun <T> put(path: String, body: Any, headers: Map<String, String>): T = request(HttpMethod.Put, path, body, headers)
//
//    override suspend fun <T> delete(path: String, headers: Map<String, String>): T = request(HttpMethod.Delete, path, null, headers)
//
//    override suspend inline fun <reified T> request(method: HttpMethod, path: String, body: Any?, headers: Map<String, String>): T {
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
//}