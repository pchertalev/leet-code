plugins {
    kotlin("jvm") version "1.7.10"
    java
}

group = "com.cpn"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.4.21")
    testImplementation("junit", "junit", "4.13.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}