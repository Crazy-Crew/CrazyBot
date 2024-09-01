import com.ryderbelserion.feather.tools.latestCommitHash

plugins {
    id("com.ryderbelserion.feather-core") version "0.0.4"

    alias(libs.plugins.shadowJar)

    application

    java
}

rootProject.group = "me.badbones69.crazybot"
rootProject.version = latestCommitHash()

repositories {
    maven("https://repo.crazycrew.us/releases")

    maven("https://jitpack.io")

    mavenCentral()
}

dependencies {
    implementation(libs.adventureLogger)

    implementation(libs.vital.common)

    implementation(libs.jetbrains)

    implementation(libs.logback)

    implementation(libs.jda) {
        exclude("club.minnced", "opus-java")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
        vendor.set(JvmVendorSpec.AMAZON)
    }

    withJavadocJar()
    withSourcesJar()
}

tasks {
    shadowJar {
        archiveFileName.set("CrazyBot.jar")
        archiveClassifier.set("")

        mergeServiceFiles()
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
    }

    application {
        mainClass.set("${rootProject.group}.Main")
    }

    processResources {
        inputs.properties("version" to rootProject.version)

        filesMatching("build.properties") {
            expand(inputs.properties)
        }
    }
}