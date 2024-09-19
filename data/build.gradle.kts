import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")

if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}
val apiKey = localProperties.getProperty("DATA_POTAL_API_KEY","")
val exchangeRateKey = localProperties.getProperty("EXCHANGE_RATE_API_KEY","")
val googlePlaceKey = localProperties.getProperty("GOOGLE_PLACE_API_KEY","")
val naverClientId = localProperties.getProperty("NAVER_CLIENT_ID","")
val naverClientSecret = localProperties.getProperty("NAVER_CLIENT_SECRET","")
android {
    namespace = "com.example.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "DATA_POTAL_API_KEY", "\"$apiKey\"")
        buildConfigField("String", "EXCHANGE_RATE_API_KEY", "\"$exchangeRateKey\"")
        buildConfigField("String", "GOOGLE_PLACE_API_KEY", "\"$googlePlaceKey\"")
        buildConfigField("String", "NAVER_CLIENT_ID", "\"$naverClientId\"")
        buildConfigField("String", "NAVER_CLIENT_SECRET", "\"$naverClientSecret\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.runner)

    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.scalar)

    implementation(libs.xml.annotation)
    implementation(libs.xml.core)
    implementation(libs.xml.retrofit)
    kapt(libs.xml.processor)
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)

    implementation(libs.google.places)
}

