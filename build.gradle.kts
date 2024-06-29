plugins {
    id("io.github.goooler.shadow") version "8.1.7"

    application

    java
}

rootProject.group = "me.badbones69.crazybot"
rootProject.version = "1.1.1"

repositories {
    maven("https://repo.crazycrew.us/releases")

    maven("https://jitpack.io")

    mavenCentral()
}

dependencies {
    implementation("com.github.Carleslc.Simple-YAML", "Simple-Yaml", "1.8.4") {
        exclude("org.yaml", "snakeyaml")
    }

    implementation("ch.qos.logback", "logback-classic", "1.5.6")

    implementation("com.ryderbelserion.vital", "core", "73050b4")

    implementation("org.jetbrains", "annotations", "24.1.0")

    implementation("net.dv8tion", "JDA", "5.0.0-beta.24") {
        exclude("club.minnced", "opus-java")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
        vendor.set(JvmVendorSpec.ADOPTIUM)
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
}