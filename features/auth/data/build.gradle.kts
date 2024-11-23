plugins {
    id("android-setup")
    id("multiplatform-compose-setup")
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.features.auth.domain)
            implementation(projects.core)
            implementation(Dependencies.Ktor.client_core)
            implementation(Dependencies.Ktor.serialization)
        }
    }
}