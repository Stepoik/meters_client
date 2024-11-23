rootProject.name = "CourseMeters"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
include(":features:auth:presentation")
include(":features:auth:data")
include(":features:auth:domain")

include(":features:home:main")
include(":features:home:flow")

include(":features:home:profile:presentation")
include(":features:home:profile:domain")
include(":features:home:profile:data")

include(":features:home:meters:presentation")
include(":features:home:meters:domain")
include(":features:home:meters:data")

include(":features:splash")

include(":features:common")
include(":uikit")
include(":core")
include(":root")