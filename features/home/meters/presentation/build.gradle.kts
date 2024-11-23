plugins {
    id("android-compose-setup")
    id("multiplatform-setup")
    id("multiplatform-compose-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.home.flow)
            implementation(projects.features.home.meters.domain)
            implementation(projects.features.home.meters.data)
            implementation(projects.core)
            implementation(projects.uikit)
        }
    }
}