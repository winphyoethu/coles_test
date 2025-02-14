package com.winphyoethu.coles.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.the

val Project.libs
    get(): VersionCatalog = the<VersionCatalogsExtension>().named("libs")

fun DependencyHandlerScope.apiProject(path: String) {
    add("api", project(path))
}

fun DependencyHandlerScope.implementation(provider: Provider<MinimalExternalModuleDependency>) {
    add("implementation", provider)
}

fun DependencyHandlerScope.implementationProject(path: String) {
    add("implementation", project(path))
}

fun DependencyHandlerScope.implementationBom(provider: Provider<MinimalExternalModuleDependency>) {
    add("implementation", platform(provider))
}

fun DependencyHandlerScope.ksp(provider: Provider<MinimalExternalModuleDependency>) {
    add("ksp", provider)
}

fun DependencyHandlerScope.testImplementation(provider: Provider<MinimalExternalModuleDependency>) {
    add("testImplementation", provider)
}

fun DependencyHandlerScope.androidTestImplementation(provider: Provider<MinimalExternalModuleDependency>) {
    add("androidTestImplementation", provider)
}

fun DependencyHandlerScope.androidTestImplementationBom(provider: Provider<MinimalExternalModuleDependency>) {
    add("androidTestImplementation", platform(provider))
}

fun DependencyHandlerScope.debugImplementation(provider: Provider<MinimalExternalModuleDependency>) {
    add("debugImplementation", provider)
}