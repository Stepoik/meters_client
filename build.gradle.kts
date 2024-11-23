plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    id(Plugins.Android.Application.plugin) apply false
    id(Plugins.Android.Library.plugin) apply false
    id(Plugins.Compose.plugin) apply false
    id(Plugins.Compose.Compiler.plugin) apply false
    id(Plugins.Multiplatform.plugin) apply false
}