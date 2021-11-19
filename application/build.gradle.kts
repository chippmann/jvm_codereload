
plugins {
    kotlin("jvm") version "1.6.0"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "org.utopia-rise"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.utopia-rise:library")
    implementation("com.utopia-rise:classloader")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "godot.application.MainKt"
    }
}
