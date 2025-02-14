plugins {
    id("coles.android.library")
    id("coles.android.library.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.winphyoethu.coles.model"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(project(":core:common"))
    implementation(libs.kotlinx.serialization.json)

}