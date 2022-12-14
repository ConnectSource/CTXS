import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("io.ktor.plugin") version "2.1.2" // JAR archiver
    application
}

group = "DxltaMath"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin.community.ssl", "ssl-plugin", "5.1.1")
    implementation("io.javalin", "javalin", "5.1.1")
    implementation("org.slf4j", "slf4j-simple", "2.0.1")
    implementation("org.junit.jupiter:junit-jupiter:5.9.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.7.10")
    implementation(kotlin("script-runtime"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

application {
    mainClass.set("ctxs.MainKt")
}

ktor {
    fatJar {
        archiveFileName.set("Nil3.jar")
    }
}
