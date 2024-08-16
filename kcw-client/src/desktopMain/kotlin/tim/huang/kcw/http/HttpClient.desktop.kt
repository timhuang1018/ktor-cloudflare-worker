package tim.huang.kcw.http

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.java.Java


actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Java) {
    config(this)
    engine {
    }
}
