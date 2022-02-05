package org.d00d

import org.gradle.api.Plugin
import org.gradle.api.Project

class IncludedPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        if (project.gradle.parent) {
            def rootProject = project.gradle.parent.rootProject
            rootProject.pluginManager.withPlugin('com.d00d.root-plugin', {
                rootProject.tasks.withType(CheckIncludedProjects).configureEach {
                    it.registeredProjects.add(project.name)
                }
            })
        }
    }
}
