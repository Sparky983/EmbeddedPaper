plugins {
    `java-library`
    `maven-publish`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    compileOnly("org.jetbrains:annotations:23.0.0")
}
