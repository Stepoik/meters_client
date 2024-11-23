plugins {
    id("android-setup")
    id("multiplatform-setup")
    id("multiplatform-compose-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.common)
            implementation(projects.features.home.flow)
            implementation(projects.features.home.meters.presentation)
            implementation(projects.features.home.profile.presentation)
        }
    }
}