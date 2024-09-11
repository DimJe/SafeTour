plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
dependencies {
    implementation(kotlin("stdlib"))
    implementation(libs.kotlin.coroutine)
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}