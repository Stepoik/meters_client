import org.gradle.kotlin.dsl.kotlin

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.android.library")
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(Dependencies.Koin.core)
            implementation(Dependencies.Kotlin.coroutines)
        }
    }
}