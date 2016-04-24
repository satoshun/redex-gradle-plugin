package com.github.satoshun.redex.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RedexTask extends DefaultTask {

    private static final Logger LOG = LoggerFactory.getLogger(RedexTask.class)

    @Input
    String redexPath

    @Input
    String srcApk

    @Input
    String destApk

    @Input
    String storePath

    @Input
    String storePassword

    @Input
    String keyAlias

    @Input
    String keyPass

    @TaskAction
    void runRedex() {
        def cmd = ["/bin/sh", "-c", "echo '${storePassword}' | ${redexPath} ${srcApk} -o ${destApk} --sign -s ${storePath} -a ${keyAlias} -p '${keyPass}'"]
        LOG.info "Evaluating", cmd.toString()

        def process = cmd.execute()
        process.waitFor()

        if (process.exitValue()) {
            throw new IllegalArgumentException(process.getErrorStream().toString())
        }

        LOG.info process.text
        println "Redex APK ${destApk}"
    }
}
