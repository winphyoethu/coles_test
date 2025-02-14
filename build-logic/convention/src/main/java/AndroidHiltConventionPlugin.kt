import com.winphyoethu.coles.convention.implementation
import com.winphyoethu.coles.convention.ksp
import com.winphyoethu.coles.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            dependencies {
                ksp(libs.findLibrary("hilt-compiler").get())
            }

            pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
                dependencies {
                    implementation(libs.findLibrary("hilt-core").get())
                }
            }

            pluginManager.withPlugin("com.android.base") {
                pluginManager.apply("dagger.hilt.android.plugin")
                dependencies {
                    implementation(libs.findLibrary("hilt-android").get())
                }
            }

        }
    }

}