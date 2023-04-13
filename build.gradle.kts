plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    google()
    maven { url  = uri("https://jitpack.io") }
    flatDir {
        dirs("lib")
    }
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("io.github.serpro69:kotlin-faker:1.9.0")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.2")

    implementation("com.itextpdf:itext7-core:7.2.3")
    implementation("org.slf4j:slf4j-nop:2.0.5")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}