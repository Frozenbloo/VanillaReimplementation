plugins {
    java
    `java-library`
    id("com.github.harbby.gradle.serviceloader") version ("1.1.8")
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    withJavadocJar()
    withSourcesJar()
}

dependencies {
    implementation(project(":core"))
    implementation(project(":world-generation"))
    implementation(project(":instance-meta"))
    implementation(project(":commands"))
    implementation(project(":block-update-system"))
    implementation(project(":vanilla-blocks"))
}

subprojects {

    plugins.apply("java")
    plugins.apply("java-library")
    plugins.apply("maven-publish")
    plugins.apply("com.github.harbby.gradle.serviceloader")

    group = "net.minestom.vanilla"
    version = "indev"

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

        withJavadocJar()
        withSourcesJar()
    }

    repositories {
        mavenCentral()
        maven(url = "https://jitpack.io")
    }

    serviceLoader.serviceInterfaces.add("net.minestom.vanilla.VanillaReimplementation\$Feature")
}