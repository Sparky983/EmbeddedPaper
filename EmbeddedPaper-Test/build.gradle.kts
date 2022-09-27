plugins {
    `java-library`
}

dependencies {
    implementation(project(":paper-api"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
    testImplementation(project(":embeddedpaper-api"))
    testRuntimeOnly(project(":paper-server"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<Test>().configureEach {
    setForkEvery(1)
}
