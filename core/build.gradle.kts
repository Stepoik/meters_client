plugins {
    id("android-setup")
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(Dependencies.Ktor.client_okhttp)
        }

        commonMain.dependencies {
            api(Dependencies.Datastore.datastore)
            api(Dependencies.Ktor.client_core)
            api(Dependencies.Jetbrains.Serialization.gson)
            api(Dependencies.Ktor.auth)
            implementation(Dependencies.Ktor.serialization)
            implementation(Dependencies.Ktor.content_negotiation)
            implementation(Dependencies.Ktor.json_serialization)
        }

        iosMain.dependencies {
            implementation(Dependencies.Ktor.client_darwin)
        }
    }
}