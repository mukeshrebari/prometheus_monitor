rootProject.name = "PrometheusMonitor"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

include(
    ":app",
    ":core",
    ":worker",
    ":storage",
    ":analytics"
)
