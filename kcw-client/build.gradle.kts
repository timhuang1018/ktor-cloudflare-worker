import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.mavenPublish)
    id("maven-publish")
}
//for publishToMavenLocal, can check ~/.m2 to find build artifacts
group = "io.keeppro"
version = "1.0.0"

kotlin {
    js(IR) {
        moduleName = "KCWClient"
        browser {
            commonWebpackConfig {
                outputFileName = "kcw.js"
            }
        }
    }
    
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    jvm("desktop")
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "KCWClient"
            isStatic = true
        }
    }
    
    sourceSets {
        val desktopMain by getting
        
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.bundles.ktor.common)
        }
        desktopMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.java)

        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        jsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
    }
}

android {
    namespace = "io.keeppro.kcw"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

mavenPublishing {

    // Define coordinates for the published artifact
    coordinates(
        groupId = "io.keeppro",
        artifactId = "kcw-client",
        version = "1.0.2"
    )

    // Configure POM metadata for the published artifact
    pom {
        name.set("KMP Library for interacting with Cloudflare worker")
        description.set("Kotlin multiplatform library for interacting with Cloudflare worker by building on top of ktor. Could used in target of Android, iOS, web, desktop. Helper functions to maximize speed to develop a MVP. Resources")
        inceptionYear.set("2024")
        url.set("https://github.com/timhuang1018/ktor-cloudflare-worker")

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        // Specify developer information
        developers {
            developer {
                id.set("timhuang")
                name.set("Tim Huang")
                email.set("t8522192@gmail.com")
            }
        }

        // Specify SCM information
        scm {
            url.set("https://github.com/timhuang1018/ktor-cloudflare-worker")
        }
    }

    // Configure publishing to Maven Central
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    // Enable GPG signing for all publications
    signAllPublications()
}