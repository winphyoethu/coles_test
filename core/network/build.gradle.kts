import java.util.Properties

plugins {
    id("coles.android.library")
    id("coles.android.hilt")
}

private val colesProperties = Properties().apply {
    load(rootProject.file("coles.properties").inputStream())
}

private val baseUrl = colesProperties.getProperty("BASE_URL")

android {
    namespace = "com.winphyoethu.coles.network"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField("String", "BASE_URL", baseUrl)

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

    api(libs.retrofit.core)
    api(libs.moshi)
    api(libs.moshi.adapters)
    api(libs.moshi.kotlin)
    api(libs.moshi.converter)
    implementation(libs.okhttp.logging)
    testImplementation(libs.junit)

}