import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.TaskAction
import org.jetbrains.kotlin.com.google.gson.JsonParser
import java.io.File

abstract class ValidateJsonTask : DefaultTask() {

    @get:InputDirectory
    abstract val filePath: DirectoryProperty

    @TaskAction
    fun validateJson() {
        val dir = filePath.get().asFile

        if (!dir.exists()) {
            return
        }

        val jsonFiles = dir.walkTopDown()
            .filter { it.isFile && it.extension == "json" }
            .filter { "build" !in it.toPath().toString().split(File.separator) }
            .toList()

        var allValid = true

        jsonFiles.forEach { file ->
            val json = file.readText()

            try {
                JsonParser.parseString(json)
                println("${file.absolutePath} is valid")
            } catch (e: Exception) {
                println("${file.absolutePath} is invalid :: ${e.message}")
                allValid = false
            }
        }

        if (!allValid) {
            throw GradleException("Some Json Files are not valid")
        }
    }

}