import org.gradle.api.Plugin
import org.gradle.api.Project

class ValidateJsonPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.register("validateJson", ValidateJsonTask::class.java) {
            group = "Help"
            description = "Validate Json"

            filePath.set(project.rootDir)
        }
    }

}