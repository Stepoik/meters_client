plugins {
    id("android-compose-setup")
    id("multiplatform-setup")
    id("multiplatform-compose-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.home.flow)
            implementation(projects.features.home.profile.data)
            implementation(projects.features.home.profile.domain)
            implementation(projects.core)
            implementation(projects.uikit)
        }
    }
}