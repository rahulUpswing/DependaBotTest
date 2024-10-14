plugins {
	kotlin("jvm") version "2.0.21"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
	id ("org.owasp.dependencycheck") version "10.0.1"
}



group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}

}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation("org.springframework:spring-core:4.1.4.RELEASE")
	implementation("org.springframework:spring-web:5.3.6")
	implementation("org.springframework:spring-webmvc:6.0.1")
	implementation("org.springframework:spring-expression:5.2.17.RELEASE")
	implementation("org.springframework:spring-expression:5.2.17.RELEASE")

	implementation("org.json:json:20220924")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}

}

tasks.withType<Test> {
	useJUnitPlatform()
}
configure<org.owasp.dependencycheck.gradle.extension.DependencyCheckExtension> {
	failBuildOnCVSS = 11.0F  // Kotlin requires explicit typing, hence the decimal
}
