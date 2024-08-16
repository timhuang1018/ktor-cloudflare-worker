package tim.huang.ktor_cloudflare_worker.utils

object UrlBuilder {
    fun buildUrl(baseUrl: String, path: String): String {
        return baseUrl.removeSuffix("/") + "/" + path.removePrefix("/")
    }
}