plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("AndroidApplicationCompose") {
            id = "coles.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("AndroidApplication") {
            id = "coles.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("AndroidFeature") {
            id = "coles.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("AndroidHilt") {
            id = "coles.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("AndroidLibraryCompose") {
            id = "coles.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("AndroidLibrary") {
            id = "coles.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("JvmLibrary") {
            id = "coles.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("androidLint") {
            id = "coles.android.lint"
            implementationClass = "AndroidLintConventionPlugin"
        }

        register("validateJson") {
            id = "coles.android.validateJson"
            implementationClass = "ValidateJsonPlugin"
        }
    }
}