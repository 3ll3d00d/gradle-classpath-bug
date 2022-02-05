package org.d00d

import org.gradle.api.Plugin
import org.gradle.api.Project

class RootPlugin implements Plugin<Project> {

    static final String TASK_NAME = 'checkRegistrations'

    @Override
    void apply(Project project) {
        if (project.gradle.parent == null) {
            project.logger.lifecycle("Registering ${TASK_NAME} in ${project.name}")
            project.tasks.register(TASK_NAME, CheckIncludedProjects, {
                it.description = 'Checks that all included projects have registered'
                it.outputs.upToDateWhen { false }
                it.includedProjects = project.provider {
                    project.gradle.includedBuilds.collect { it.name }
                }
            })
        }
    }
}