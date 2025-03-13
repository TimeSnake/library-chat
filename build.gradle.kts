plugins {
    id("java")
    id("java-base")
    id("java-library")
    id("maven-publish")
}


group = "de.timesnake"
version = "3.0.0"
var projectId = 59

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://git.timesnake.de/api/v4/groups/7/-/packages/maven")
        name = "timesnake"
        credentials(PasswordCredentials::class)
    }
}

dependencies {
    api("net.kyori:adventure-api:4.11.0")
    api("net.kyori:adventure-text-serializer-legacy:4.12.0")
    api("net.kyori:adventure-text-serializer-plain:4.12.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

configurations.all {
    resolutionStrategy.dependencySubstitution.all {
        requested.let {
            if (it is ModuleComponentSelector && it.group == "de.timesnake") {
                val targetProject = findProject(":${it.module}")
                if (targetProject != null) {
                    useTarget(targetProject)
                }
            }
        }
    }
}

publishing {
    repositories {
        maven {
            url = uri("https://git.timesnake.de/api/v4/projects/$projectId/packages/maven")
            name = "timesnake"
            credentials(PasswordCredentials::class)
        }
    }

    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release = 21
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
    withSourcesJar()
}

tasks.test {
    useJUnitPlatform()
}