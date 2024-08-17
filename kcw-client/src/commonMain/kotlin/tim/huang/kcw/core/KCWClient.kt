package tim.huang.kcw.core


interface KCWClient {
//    suspend fun <T> get(path: String, headers: Map<String, String> = emptyMap()): T
//    suspend fun <T> post(path: String, body: Any, headers: Map<String, String> = emptyMap()): T
//    suspend fun <T> put(path: String, body: Any, headers: Map<String, String> = emptyMap()): T
//    suspend fun <T> delete(path: String, headers: Map<String, String> = emptyMap()): T
//    suspend fun <T> request(method: HttpMethod, path: String, body: Any? = null, headers: Map<String, String> = emptyMap()): T

    suspend fun uploadFile(path: String, byteArray: ByteArray, contentType: String, headers: Map<String, String> = emptyMap()): String

    companion object {
        fun create(config: KCWConfig): KCWClient = KCWClientImpl(config)
    }
}