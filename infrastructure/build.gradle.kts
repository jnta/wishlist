plugins {
    java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "br.com.jonataalbuquerque"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

ext {
    set("springdocVersion", "2.6.0")
    set("testcontainersVersion", "1.20.3")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${property("springdocVersion")}")
    testImplementation("org.testcontainers:junit-jupiter:${property("testcontainersVersion")}")
    testImplementation("org.testcontainers:mongodb:${property("testcontainersVersion")}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.cucumber:cucumber-java:7.0.0")
    testImplementation("io.cucumber:cucumber-spring:7.0.0")
    testImplementation("io.cucumber:cucumber-junit:7.0.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

configurations {
    create("cucumberRuntime") {
        extendsFrom(configurations["testImplementation"])
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.register("cucumberCli") {
    description = "Run Cucumber CLI"
    group = "verification"
    dependsOn("assemble", "testClasses")
    doLast {
        javaexec {
            mainClass.set("io.cucumber.core.cli.Main")
            classpath = configurations["cucumberRuntime"] + sourceSets["main"].output + sourceSets["test"].output
            args = listOf(
                "--plugin", "pretty",
                "--plugin", "html:target/cucumber-report.html",
                "--glue", "wishlist.infrastructure.controller",
                "src/test/resources"
            )
        }
    }
}