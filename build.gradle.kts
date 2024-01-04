

plugins {
  id("uk.gov.justice.hmpps.gradle-spring-boot") version "5.10.1"
  kotlin("plugin.spring") version "1.9.21"
  kotlin("plugin.jpa") version "1.9.21"
  kotlin("plugin.lombok") version "1.9.21"
  id("name.remal.integration-tests") version "4.0.2"
  id("jvm-test-suite")
}

configurations {
  implementation {
    exclude(module = "commons-logging")
    exclude(module = "log4j")
  }
  testImplementation { exclude(group = "org.junit.vintage") }
}

testing {
  suites {
    val test by getting(JvmTestSuite::class) {
      useJUnitJupiter()
    }
  }
}

dependencies {
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
  annotationProcessor("org.projectlombok:lombok:1.18.30")
  testAnnotationProcessor("org.projectlombok:lombok:1.18.30")

  implementation("org.springframework.boot:spring-boot-starter-aop")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-jdbc")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.boot:spring-boot-starter-cache")
  developmentOnly("org.springframework.boot:spring-boot-devtools")

  implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

  implementation("commons-codec:commons-codec:1.16.0")
  implementation("io.swagger:swagger-annotations:1.6.12")
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

  implementation("org.apache.commons:commons-lang3:3.14.0")
  implementation("commons-io:commons-io:2.15.1")
  implementation("com.google.guava:guava:32.1.3-jre")

  implementation("org.ehcache:ehcache:3.10.8")
  implementation("com.zaxxer:HikariCP:5.1.0")
  implementation("com.oracle.database.jdbc:ojdbc10:19.21.0.0")
  implementation("org.hibernate.orm:hibernate-community-dialects")

  testImplementation("org.springframework.security:spring-security-test")
  testImplementation("io.jsonwebtoken:jjwt-impl:0.12.3")
  testImplementation("io.jsonwebtoken:jjwt-jackson:0.12.3")
  testImplementation("com.h2database:h2")
}

kotlin {
  jvmToolchain(21)
}

tasks {
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
      jvmTarget = "21"
    }
    named("check") {
      dependsOn(testing.suites.named("integrationTest"))
    }
    named("compileIntegrationTestKotlin") {
      dependsOn(named("copyAgent"))
    }
    named("assemble") {
      // `assemble` task assembles the classes and dependencies into a fat jar
      // Beforehand we need to remove the plain jar and test-fixtures jars if they exist
      doFirst {
        delete(
          fileTree(project.layout.buildDirectory.get())
            .include("libs/*-plain.jar")
        )
      }
    }
  }
}
