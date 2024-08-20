
# Ktor Cloudflare Worker Library

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/badge/kotlin-multiplatform-blue)](https://kotlinlang.org/)

## Overview

Welcome to the **Ktor Cloudflare Worker Library**, a Kotlin multiplatform library that simplifies interaction with Cloudflare Workers by building on top of the powerful [Ktor](https://ktor.io/) framework. Whether you're targeting Android, iOS, web, or desktop, this library provides the helper functions you need to rapidly develop a Minimum Viable Product (MVP).

By using this library, you can leverage the full power of Kotlin Multiplatform to create robust, client-side applications across all major platforms—without sacrificing speed or flexibility.

## Why Use This Library?

- **Fast MVP Development:** Focus on building your application's features, not on low-level implementation details. This library simplifies the integration with Cloudflare Workers, letting you build and iterate on your MVP faster.
- **Multiplatform Ready:** Whether you're building for Android, iOS, web, or desktop, `ktor-cloudflare-worker` supports it all, thanks to Kotlin's powerful multiplatform capabilities.
- **Ktor-based:** Build on top of the familiar and flexible Ktor framework, designed for modern Kotlin applications.

## Features

- **Unified API:** Write code once and deploy it across all supported platforms.
- **Helper Functions:** Streamlined utility functions to interact with Cloudflare Workers effortlessly.
- **Scalable Architecture:** Start with an MVP and scale it into a fully-fledged product without needing to refactor your backend interactions.

## Getting Started

### Installation

Add the following dependency to your `build.gradle.kts`, could be used in common or any target(Android, iOS, web, and desktop):

```kotlin
implementation("io.keeppro:ktor-cloudflare-worker:1.0.2")
```

```

### Usage

Check out the [examples](https://github.com/timhuang1018/ktor-cloudflare-worker/tree/main/examples) directory to see how you can integrate this library into your projects.

```kotlin
        coroutineScope.launch(exceptionHandler) {
            //request to worker through KCWClient
            val kcwClient: KCWClient = KCWClient.create(
                KCWConfig(baseUrl = getString(Res.string.base_url))
            )
            kcwClient.uploadFile(
                path = "projects/test123/logo",
                contentType = "image/jpeg",
                byteArray = Res.readBytes("files/display_pad.jpeg")
            )
            state.value = UploadState.Success
        }

```

The library provides simple, yet powerful, functions to interact with your Cloudflare Workers seamlessly.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgments

- [Ktor](h
# Ktor Cloudflare Worker Library

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/badge/kotlin-multiplatform-blue)](https://kotlinlang.org/)

## Overview

Welcome to the **Ktor Cloudflare Worker Library**, a Kotlin multiplatform library that simplifies interaction with Cloudflare Workers by building on top of the powerful [Ktor](https://ktor.io/) framework. Whether you're targeting Android, iOS, web, or desktop, this library provides the helper functions you need to rapidly develop a Minimum Viable Product (MVP).

By using this library, you can leverage the full power of Kotlin Multiplatform to create robust, client-side applications across all major platforms—without sacrificing speed or flexibility.

## Why Use This Library?

- **Fast MVP Development:** Focus on building your application's features, not on low-level implementation details. This library simplifies the integration with Cloudflare Workers, letting you build and iterate on your MVP faster.
- **Multiplatform Ready:** Whether you're building for Android, iOS, web, or desktop, `ktor-cloudflare-worker` supports it all, thanks to Kotlin's powerful multiplatform capabilities.
- **Ktor-based:** Build on top of the familiar and flexible Ktor framework, designed for modern Kotlin applications.

## Features

- **Unified API:** Write code once and deploy it across all supported platforms.
- **Helper Functions:** Streamlined utility functions to interact with Cloudflare Workers effortlessly.
- **Scalable Architecture:** Start with an MVP and scale it into a fully-fledged product without needing to refactor your backend interactions.

## Getting Started

### Installation

Add the following dependency to your `build.gradle.kts`, could be used in common or any target(Android, iOS, web, and desktop):

```kotlin
implementation("io.keeppro:ktor-cloudflare-worker:1.0.2")
```

```

### Usage

Check out the [examples](https://github.com/timhuang1018/ktor-cloudflare-worker/tree/main/examples) directory to see how you can integrate this library into your projects.

```kotlin
val client = KCWClientImpl()
client.get("https://your-cloudflare-worker-url")
```

The library provides simple, yet powerful, functions to interact with your Cloudflare Workers seamlessly.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgments

- [Ktor](https://ktor.io/) for providing a solid foundation for HTTP clients in Kotlin.ttps://ktor.io/) for providing a solid foundation for HTTP clients in Kotlin.

## Showcase

This library is already powering production applications! Check out [KeepPro](https://keeppro.io), a [brief description of the app] built using `ktor-cloudflare-worker` for seamless interactions with Cloudflare Workers across multiple platforms.

If you're using this library in your project, we'd love to hear from you! Feel free to open a PR and add your app to this showcase.