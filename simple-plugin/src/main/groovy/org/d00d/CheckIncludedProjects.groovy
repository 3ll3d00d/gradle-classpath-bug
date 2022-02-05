package org.d00d

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class CheckIncludedProjects extends DefaultTask {
    @Input
    abstract ListProperty<String> getRegisteredProjects()
    @Input
    Provider<List<String>> includedProjects

    @TaskAction
    void run() {
        project.logger.lifecycle("Included: ${includedProjects.get()}")
        project.logger.lifecycle("Registered: ${registeredProjects.get()}")
        Set<String> missingProjects = (includedProjects.get() as Set<String>) - registeredProjects.get() as Set<String>

        if (includedProjects.get().size() == registeredProjects.get().size() && missingProjects.isEmpty()) {
            project.logger.lifecycle("OK!")
        } else {
            throw new GradleException("Projects did not register - ${missingProjects}")
        }
    }
}
