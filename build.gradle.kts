import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootWar

val aspectJVersion = "1.9.22"
val allureVersion = "2.29.1"

val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

plugins {
	war
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
    id("io.qameta.allure") version "2.11.2"

	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"


java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
    agent("org.aspectj:aspectjweaver:$aspectJVersion")

    implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.slf4j:slf4j-api:2.0.7")
	implementation("ch.qos.logback:logback-classic:1.4.7")

	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("junit:junit:4.13.2")

    testImplementation("io.rest-assured:kotlin-extensions:5.3.0")
	testImplementation("io.rest-assured:json-schema-validator:5.3.0")

    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-junit4")
    testImplementation("io.qameta.allure:allure-junit4-aspect")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
    ignoreFailures = true
    useJUnit() // JUnit4
//    testLogging {
//        events("passed", "skipped", "failed")
//    }
//    jvmArgs = listOf(
//        "-javaagent:${agent.singleFile}"
//    )
}

tasks.named<BootWar>("bootWar") {
    enabled = false
}
