plugins {
    id("android-setup")
    id("multiplatform-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.features.home.meters.domain)
            api(projects.core)
        }
    }
}