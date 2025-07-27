plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "ru.kpfu.itis.springpractice.experiment"
    compileSdk = 36

    defaultConfig {
        applicationId = "ru.kpfu.itis.springpractice.experiment"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        defaultConfig {
            buildConfigField(
                "String",
                "ADVENTURER_APP_BASE_URL",
                "\"http://10.0.2.2:8080/api/\"")
        }

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.viewbindingpropertydelegate.noreflection)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
//    implementation(libs.retrofit.converters)
    implementation(libs.kotlinx.coroutines.android)
//    implementation(libs.logging.interceptor)
}