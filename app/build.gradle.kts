plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.gms.google.services)
}

android {
    namespace = "kh.com.pheaktra.developer.basic.android"
    compileSdk = 36

    defaultConfig {
        applicationId = "kh.com.pheaktra.developer.basic.android"
        minSdk = 29
        targetSdk = 37
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.coil.compose)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.runtime)
//    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
//    implementation(libs.androidx.material3.adaptive.navigation3)
//    implementation(libs.kotlinx.serialization.core)

    // Permission
    implementation(libs.com.google.accompanist.permissions)

    // Retrofit Client
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.logging.interceptor)

    implementation(libs.org.jetbrains.kotlinx.serialization.json)
//    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.jakewharton.retrofit2.kotlinx.serialization.converter)

    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Room database
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    // Firebase push notification
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    // Local module
    implementation(project(":core"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}