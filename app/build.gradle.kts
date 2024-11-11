plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
}

android {
    namespace = "com.dicoding.azerif"
    compileSdk = 30

    defaultConfig {
        applicationId = "com.dicoding.azerif"
        minSdk = 26

        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

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

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
//  configured to support Java 8 (JDK 30) compatibility
    implementation(libs.androidx.core.ktx.v132)
    implementation(libs.androidx.appcompat.v120)
    implementation(libs.material.v130)
    implementation(libs.androidx.activity.ktx.v121)
    implementation(libs.androidx.constraintlayout.v204)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v112)
    androidTestImplementation(libs.androidx.espresso.core.v330)
    implementation(libs.circleimageview)
    implementation(libs.androidsvg)

//    newer version
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat.v161)
//    implementation(libs.material)
//    implementation(libs.androidx.activity)
//    implementation(libs.androidx.constraintlayout)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    implementation(libs.androidx.core.splashscreen)
//    implementation(libs.circleimageview)
//    implementation(libs.androidsvg.aar)

}