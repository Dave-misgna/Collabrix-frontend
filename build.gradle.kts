plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("org.jetbrains.kotlin.kapt") version "2.0.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
    id("org.jetbrains.compose") version "1.5.11" apply false
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("org.jetbrains.kotlin.kapt") version "2.0.0" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
    id("org.jetbrains.compose") version "1.5.11" apply false
}

// Configure repositories for all projects
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}