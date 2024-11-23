plugins {
    id("android-compose-setup")
    id("multiplatform-setup")
    id("multiplatform-compose-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.common)
            implementation(projects.uikit)
            implementation(projects.features.auth.domain)
            implementation(projects.features.auth.data)
            implementation(projects.core)
        }
    }
}