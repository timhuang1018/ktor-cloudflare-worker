package tim.huang.ktor_cloudflare_worker.http

import io.ktor.client.*
import io.ktor.client.engine.darwin.*


actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Darwin) {
    config(this)
    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
}