package tim.huang.kcw.core

data class KCWConfig(
    val baseUrl: String,
    val timeout: Long = 30_000,
    val retries: Int = 3
)
