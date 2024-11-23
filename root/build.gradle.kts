plugins {
    id("android-setup")
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.auth.presentation)
            implementation(projects.features.home.meters.presentation)
            implementation(projects.features.home.profile.presentation)
            implementation(projects.features.splash)
            implementation(projects.core)
        }
    }
}