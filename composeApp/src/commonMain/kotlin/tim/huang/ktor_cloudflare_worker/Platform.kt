package tim.huang.ktor_cloudflare_worker

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform