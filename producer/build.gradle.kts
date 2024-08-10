plugins {
    id("org.springframework.boot") version "3.3.2" apply false
    id("io.spring.dependency-management") version "1.1.6"
    `java-library`
    `maven-publish`
    jacoco
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter-amqp:3.2.2")

    // Api
    implementation(project(":api"))
    implementation(project(":starter"))
    implementation(project(":core"))
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "producer"

            from(components["java"])

            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}