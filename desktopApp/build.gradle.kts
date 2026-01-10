import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting  {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":shared"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "MathSolver"
            packageVersion = "1.0.0"

            buildTypes.release.proguard {
                version.set("7.4.2")
                isEnabled.set(true)
                configurationFiles.from(project.file("proguard-rules.pro"))
            }
        }
    }
}

// Task to build GraalVM native image
tasks.register<Exec>("nativeCompile") {
    group = "build"
    description = "Builds a GraalVM native image"
    dependsOn("packageUberJarForCurrentOS")
    
    doFirst {
        val jarFile = layout.buildDirectory.file("compose/jars/MathSolver-${project.version}.jar").get().asFile
        if (!jarFile.exists()) {
            throw GradleException("Uber JAR not found at ${jarFile.absolutePath}. Run packageUberJarForCurrentOS first.")
        }
    }
    
    commandLine(
        "native-image",
        "-jar", layout.buildDirectory.file("compose/jars/MathSolver-${project.version}.jar").get().asFile.absolutePath,
        "-H:Name=math-solver",
        "-H:+ReportExceptionStackTraces",
        "--no-fallback",
        "--report-unsupported-elements-at-runtime",
        "--initialize-at-build-time=kotlin,kotlinx",
        "-H:IncludeResources=.*",
        "-H:+AddAllCharsets",
        "-o", layout.buildDirectory.file("native/math-solver").get().asFile.absolutePath
    )
    
    doFirst {
        layout.buildDirectory.dir("native").get().asFile.mkdirs()
    }
}

tasks.register("nativeRun") {
    group = "application"
    description = "Runs the GraalVM native image"
    dependsOn("nativeCompile")
    
    doLast {
        exec {
            commandLine(layout.buildDirectory.file("native/math-solver").get().asFile.absolutePath)
        }
    }
}
