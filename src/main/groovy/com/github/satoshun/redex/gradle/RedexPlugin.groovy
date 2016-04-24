package com.github.satoshun.redex.gradle

import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaBasePlugin
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RedexPlugin implements Plugin<Project> {

    private static final Logger LOG = LoggerFactory.getLogger(RedexPlugin.class)

    @Override
    void apply(Project project) {
        def android = project.extensions.getByType(AppExtension)

        project.extensions.add "redex", RedexExtension

        android.applicationVariants.all { BaseVariant variant ->

            final String name = variant.name
            final RedexExtension config = project.redex

            def assembleTask = project.task("assembleRedex${name.capitalize()}",
                    type: RedexTask) {
                group = JavaBasePlugin.VERIFICATION_GROUP
                description = "Assemble redex $name variation"

                redexPath = config.redexPath ? config.redexPath : "redex"
                srcApk = "$project.buildDir/outputs/apk/${project.name}-${name}.apk"
                destApk = "$project.buildDir/outputs/apk/${project.name}-redex-${name}.apk"
                storePath = config.storePath
                storePassword = config.storePassword
                keyAlias = config.keyAlias
                keyPass = config.keyPass
            } as RedexTask
            assembleTask.dependsOn "assemble${name.capitalize()}"

            def installTask = project.task("installRedex${name.capitalize()}",
                    type: InstallTask) {
                group = JavaBasePlugin.VERIFICATION_GROUP
                description = "Install redex $name variation"

                adbPath = config.adbPath ? config.adbPath : "adb"
                srcApk = "$project.buildDir/outputs/apk/${project.name}-redex-${name}.apk"
            } as InstallTask
            installTask.dependsOn "assembleRedex${name.capitalize()}"
        }
    }
}
