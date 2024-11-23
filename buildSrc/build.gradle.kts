plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
    google()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(Dependencies.Android.libraryGradlePlugin)
    implementation(Dependencies.Kotlin.gradlePlugin)
    implementation(Dependencies.Compose.gradlePlugin)
    implementation(Dependencies.Compose.Compiler.gradlePlugin)
    implementation(Dependencies.Jetbrains.Serialization.gradlePlugin)
    implementation(Dependencies.Room.gradlePlugin)
    implementation(Dependencies.KSP.gradlePlugin)
    implementation(Dependencies.Apollo.gradlePlugin)
}

kotlin {
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}