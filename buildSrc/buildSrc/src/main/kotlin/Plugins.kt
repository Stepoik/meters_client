object Plugins {
    object Android {
        private const val agpVersion = Dependencies.Android.version

        object Library {
            const val version = agpVersion
            const val plugin = "com.android.library"
        }

        object Application {
            const val version = agpVersion
            const val plugin = "com.android.application"
        }
    }

    object Compose {
        const val version = Dependencies.Compose.pluginVersion
        const val plugin = "org.jetbrains.compose"

        object Compiler {
            const val version = Dependencies.Kotlin.version
            const val plugin = "org.jetbrains.kotlin.plugin.compose"
        }
    }

    object Multiplatform {
        const val version = Dependencies.Kotlin.version
        const val plugin = "org.jetbrains.kotlin.multiplatform"
    }

    object Apollo {
        const val version = Dependencies.Apollo.version
        const val plugin = "com.apollographql.apollo"
    }
}