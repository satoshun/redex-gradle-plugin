package com.github.satoshun.redex.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class InstallTask extends DefaultTask {

    private static final Logger LOG = LoggerFactory.getLogger(InstallTask.class)

    @Input
    String adbPath

    @Input
    String srcApk

    @TaskAction
    void runInstall() {
        def process = [adbPath, "install", srcApk].execute()
        process.waitFor()

        if (process.exitValue()) {
            throw new IllegalArgumentException("${process.getErrorStream()}")
        }

        LOG.info process.text
        println "Install APK ${srcApk}"
    }
}
