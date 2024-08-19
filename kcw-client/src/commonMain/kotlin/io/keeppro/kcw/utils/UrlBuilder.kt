package io.keeppro.kcw.utils

object UrlBuilder {
    fun buildUrl(baseUrl: String, path: String): String {
        return baseUrl.removeSuffix("/") + "/" + path.removePrefix("/")
    }
}