plugins {
    id("android-setup")
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.features.home.profile.domain)
            api(projects.core)
        }
    }
}