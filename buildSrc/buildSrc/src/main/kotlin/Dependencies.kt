object Dependencies {
    object Kotlin {
        const val version = "2.0.20"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        private const val coroutinesVersion = "1.9.0"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    }

    object Compose {
        const val pluginVersion = "1.7.0-alpha02"
        const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:$pluginVersion"

        object Compiler {
            const val gradlePlugin =
                "org.jetbrains.kotlin:compose-compiler-gradle-plugin:${Kotlin.version}"
        }

        const val viewModel = "org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.8.3"
        const val navigation = "org.jetbrains.androidx.navigation:navigation-compose:2.8.0-alpha09"
    }

    object Koin {
        private const val version = "4.0.0"
        const val koinCompose = "io.insert-koin:koin-compose:$version"
        const val viewModel = "io.insert-koin:koin-compose-viewmodel:$version"

        private const val coreVersion = "4.0.0"
        const val core = "io.insert-koin:koin-core:$coreVersion"
    }

    object Android {
        const val version = "8.3.2"
        const val libraryGradlePlugin =
            "com.android.library:com.android.library.gradle.plugin:$version"
        const val applicationGradlePlugin =
            "com.android.library:com.android.library.gradle.plugin:$version"
        const val appCompat = "androidx.appcompat:appcompat:1.6.1"

        object Compose {
            private const val version = "1.8.0"
            const val activity = "androidx.activity:activity-compose:$version"
        }
    }

    object Jetbrains {
        object Serialization {
            const val gradlePlugin =
                "org.jetbrains.kotlin:kotlin-serialization:${Kotlin.version}"

            const val gson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3"
        }

        object DateTime {
            const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:0.6.1"
        }
    }

    object Datastore {
        const val datastore = "androidx.datastore:datastore-preferences-core:1.1.1"
    }

    object IceRock {
        const val biometry = "dev.icerock.moko:biometry-compose:0.4.0"
    }

    object Ditchoom {
        const val buffer = "com.ditchoom:buffer:1.4.2"
    }

    object Whyoleg {
        const val core = "dev.whyoleg.cryptography:cryptography-core:0.3.1"
        const val android = "dev.whyoleg.cryptography:cryptography-provider-jdk:0.3.1"
        const val apple = "dev.whyoleg.cryptography:cryptography-provider-apple:0.3.1"
    }

    object Room {
        const val version = "2.7.0-alpha10"
        const val sqliteVersion = "2.5.0-alpha04"
        const val roomCompiler = "androidx.room:room-compiler:$version"
        const val roomRuntime = "androidx.room:room-runtime:$version"
        const val sqlite = "androidx.sqlite:sqlite:$sqliteVersion"
        const val sqliteBundled = "androidx.sqlite:sqlite-bundled:$sqliteVersion"
        const val gradlePlugin = "androidx.room:room-gradle-plugin:${version}"
    }

    object KSP {
        const val gradlePlugin = "com.google.devtools.ksp:symbol-processing-gradle-plugin:2.0.20-1.0.25"
    }

    object Ktor {
        const val version = "2.3.12"
        const val client_core = "io.ktor:ktor-client-core:$version"
        const val client_okhttp = "io.ktor:ktor-client-okhttp:$version"
        const val client_darwin = "io.ktor:ktor-client-darwin:$version"
        const val serialization = "io.ktor:ktor-serialization-kotlinx-json:$version"
        const val content_negotiation = "io.ktor:ktor-client-content-negotiation:$version"
        const val json_serialization = "io.ktor:ktor-serialization-kotlinx-json:$version"
        const val auth = "io.ktor:ktor-client-auth:$version"
    }

    object Apache {
        const val math = "org.apache.commons:commons-math3:3.6.1"
    }

    object Apollo {
        const val version = "4.0.1"
        const val runtime = "com.apollographql.apollo:apollo-runtime:$version"
        const val gradlePlugin = "com.apollographql.apollo:apollo-gradle-plugin:$version"
    }

    object Exoplayer {
        const val version = "1.4.0"
        const val exoplayer = "androidx.media3:media3-exoplayer:$version"
        const val exoplayer_dash = "androidx.media3:media3-exoplayer-dash:$version"
        const val exoplayer_ui = "androidx.media3:media3-ui:$version"
    }
}