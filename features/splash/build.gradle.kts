plugins {
    id("android-setup")
    id("multiplatform-setup")
    id("multiplatform-compose-setup")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.features.common)
            implementation(projects.core)
            implementation(projects.uikit)
        }
    }
}