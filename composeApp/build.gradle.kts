import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id(Plugins.Multiplatform.plugin)
    id(Plugins.Android.Application.plugin)
    id(Plugins.Compose.plugin)
    id(Plugins.Compose.Compiler.plugin)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(Dependencies.Android.appCompat)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(Dependencies.Compose.navigation)
            implementation(Dependencies.Koin.core)
            implementation(projects.features.home.main)
            implementation(projects.uikit)
            implementation(projects.root)
            implementation(projects.features.auth.presentation)
            implementation(projects.features.splash)
            implementation(projects.features.common)
        }
    }

    tasks.register("assembleXCFramework") {
        dependsOn(listOf(
            "linkDebugFrameworkIosArm64",
            "linkDebugFrameworkIosSimulatorArm64"
        ))

        doLast {
            val buildDir = buildDir.resolve("bin")
            val outputDir = buildDir.resolve("xcframework")
            val iosArm64Dir = buildDir.resolve("iosArm64/debugFramework/ComposeApp.framework")
            val iosSimulatorArm64Dir = buildDir.resolve("iosSimulatorArm64/debugFramework/ComposeApp.framework")

            exec {
                commandLine = listOf(
                    "xcodebuild", "-create-xcframework",
                    "-framework", iosArm64Dir.absolutePath,
                    "-framework", iosSimulatorArm64Dir.absolutePath,
                    "-output", outputDir.resolve("ComposeApp.xcframework").absolutePath
                )
            }
        }
    }
}

android {
    namespace = "goroh.stepan"
    compileSdk = 34

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "goroh.stepan"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}
