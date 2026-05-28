import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
    jacoco
    checkstyle
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.github.chatelao"
version = if (System.getenv("GITHUB_REF_TYPE") == "tag") {
    System.getenv("GITHUB_REF_NAME")?.replaceFirst(Regex("^v"), "") ?: "0.2.2"
} else {
    "0.2.2"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf("-Xlint:deprecation", "-Xlint:unchecked"))
}

println("Source Compatibility: ${java.sourceCompatibility}")

repositories {
    mavenCentral()
}

val checkstyleConfig: Configuration by configurations.creating

dependencies {
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.17.2")
    implementation("com.fasterxml.jackson.module:jackson-module-jaxb-annotations:2.17.2")
    implementation("com.fasterxml:aalto-xml:1.3.2")
    testImplementation("junit:junit:4.12")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("com.code-intelligence:jazzer-junit:0.30.0")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.10.0")

    checkstyleConfig("com.puppycrawl.tools:checkstyle:13.4.2") {
        isTransitive = false
    }
}

val sourcesJar by tasks.registering(Jar::class) {
    dependsOn(tasks.classes)
    archiveClassifier.set("sources")
    from(sourceSets.main.map { it.allSource })
}

val javadocJar by tasks.registering(Jar::class) {
    val javadocTask = tasks.javadoc
    dependsOn(javadocTask)
    archiveClassifier.set("javadoc")
    from(javadocTask.map { it.destinationDir!! })
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(sourcesJar.get())
            artifact(javadocJar.get())
            pom {
                name.set("Alpheus AFP Parser")
                description.set("Alpheus AFP Parser is a library and parser for the IBM Advanced Function Presentation (AFP) document/print stream format. Alpheus covers all AFP specifications: MO:DCA, BCOCA, CMOCA, FOCA, GOCA, IOCA, and PTOCA. It is a complete implementation. Every Structured Field, Repeating Group, and Triplet is fully implemented as Java class. Alpheus AFP Parser was written from scratch and has no external dependencies. This project is a continuation of the original Alpheus AFP Parser (https://github.com/afpdev/alpheusafpparser) by Rudolf Fiala. Copyright 2015-2026 Rudolf Fiala")
                url.set("https://github.com/chatelao/alpheusafpparser")
                inceptionYear.set("2015")
                licenses {
                    license {
                        name.set("GNU General Public License, Version 3")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.txt")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("afpdev")
                        name.set("Rudolf Fiala")
                        email.set("afpdev@mogozine.com")
                        url.set("https://github.com/afpdev")
                        roles.set(listOf("architect", "developer"))
                        timezone.set("Europe/Vienna")
                    }
                }
                scm {
                    url.set("https://github.com/chatelao/alpheusafpparser")
                    connection.set("scm:git:git://github.com/chatelao/alpheusafpparser.git")
                }
            }
        }
    }
}

tasks.register("writeNewPom") {
    dependsOn("generatePomFileForMavenJavaPublication")
    doLast {
        copy {
            from(layout.buildDirectory.file("publications/mavenJava/pom-default.xml"))
            into(layout.buildDirectory.dir("libs"))
            rename { "alpheusafpparser-${version}.pom" }
        }
    }
}

tasks.clean {
    doFirst {
        delete(layout.projectDirectory.dir("output/"))
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED, TestLogEvent.STANDARD_OUT, TestLogEvent.STANDARD_ERROR)
    }
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

artifacts {
    add("archives", sourcesJar.get())
    add("archives", javadocJar.get())
}

checkstyle {
    toolVersion = "13.4.2"
    config = resources.text.fromArchiveEntry(checkstyleConfig, "google_checks.xml")
    isIgnoreFailures = true
    isShowViolations = true
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveBaseName.set("alpheus-afp-parser-cli")
    archiveClassifier.set("")
    archiveVersion.set(version.toString())
    manifest {
        attributes("Main-Class" to "com.mgz.cli.Afp2Xml")
    }
}
