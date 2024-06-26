import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.0.0"

    application
}

base {
    archivesName.set(rootProject.name)
}

repositories {
    maven("https://repo.crazycrew.us/releases")

    mavenCentral()
}

dependencies {
    api("com.ryderbelserion.vital", "discord", "1.8.1") {
        exclude("club.minnced", "opus-java")
    }
}

kotlin {
    jvmToolchain(21)

    explicitApi()
}

tasks {
    compileKotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
            javaParameters = true
        }
    }

    application {
        mainClass.set("com.ryderbelserion.Main")
    }
}