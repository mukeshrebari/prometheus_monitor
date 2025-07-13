plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 34
    defaultConfig { minSdk = 24 }
    namespace = "com.example.prommonitor.worker"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":storage"))
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
}
