plugins {
    id("coles.android.library")
    id("coles.android.hilt")
}

android {
    namespace = "com.winphyoethu.coles.common"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
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

    api(libs.kotlinx.collections.immutable)

}