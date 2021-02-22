import java.io.FileInputStream
import java.util.Properties
import org.gradle.api.publish.PublishingExtension

plugins {
    id("kotlin-multiplatform")
    `maven-publish`
    signing
}

kotlin {
    sourceSets {
        jvm()
        js() {
            browser()
        }
        linuxX64()
        linuxArm64()
        mingwX64()
        macosX64()
        iosArm64()
        iosX64()

        val commonMain by getting {
            dependencies {
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jsMain by getting {
            dependencies {
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }

        val jvmMain by getting {
            dependencies {
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }

        val nativeMain by creating {
            dependsOn(commonMain)
            dependencies {
            }
        }

        val linuxX64Main by getting {
            dependsOn(nativeMain)
        }
        val linuxArm64Main by getting {
            dependsOn(nativeMain)
        }
        val mingwX64Main by getting {
            dependsOn(nativeMain)
        }
        val macosX64Main by getting {
            dependsOn(nativeMain)
        }
        val iosArm64Main by getting {
            dependsOn(nativeMain)
        }
        val iosX64Main by getting {
            dependsOn(nativeMain)
        }
    }

//    targets {
//        jvm() {
//            /* ... */
//            mavenPublication {
//                //artifactId = 'sample-lib-jvm'
//                artifact(jvmJavadocJar)
//            }
//        }
//    }
}

tasks {
    val javadocsJar by creating(Jar::class) {
        archiveClassifier.set("javadoc")
        from(JavaPlugin.JAVADOC_TASK_NAME)
    }
}

//--------------------------------------------------------------------------------------------------
//  Publishing
//--------------------------------------------------------------------------------------------------

val fis = FileInputStream("local.properties")
val properties = Properties().apply {
    load(fis)
}
val ossUser = properties.getProperty("oss.user")
val ossPassword = properties.getProperty("oss.password")
extra["signing.keyId"] = properties.getProperty("signing.keyId")
extra["signing.password"] = properties.getProperty("signing.password")
extra["signing.secretKeyRingFile"] = properties.getProperty("signing.secretKeyRingFile")

val libraryVersion: String by project
val publishedGroupId: String by project
val artifactName: String by project
val libraryName: String by project
val libraryDescription: String by project
val siteUrl: String by project
val gitUrl: String by project
val licenseName: String by project
val licenseUrl: String by project
val developerOrg: String by project
val developerName: String by project
val developerEmail: String by project
val developerId: String by project

project.group = publishedGroupId
project.version = libraryVersion

afterEvaluate {
    configure<PublishingExtension> {
        publications.all {
            val mavenPublication = this as? MavenPublication
            mavenPublication?.artifactId =
                "${project.name}${"-$name".takeUnless { "kotlinMultiplatform" in name }.orEmpty()}"
        }
    }
}

configure<PublishingExtension> {
    publications {
        withType<MavenPublication> {
            groupId = publishedGroupId
            artifactId = artifactName
            version = libraryVersion

            artifact(tasks.findByName("javadocsJar"))
            artifact(tasks.findByName("sourcesJar"))

            pom {
                name.set(libraryName)
                description.set(libraryDescription)
                url.set(siteUrl)

                licenses {
                    license {
                        name.set(licenseName)
                        url.set(licenseUrl)
                    }
                }
                developers {
                    developer {
                        id.set(developerId)
                        name.set(developerName)
                        email.set(developerEmail)
                    }
                }
                organization {
                    name.set(developerOrg)
                }
                scm {
                    connection.set(gitUrl)
                    developerConnection.set(gitUrl)
                    url.set(siteUrl)
                }
            }
        }
    }

    repositories {
        maven("https://oss.sonatype.org/service/local/staging/deploy/maven2/") {
            name = "sonatype"
            credentials {
                username = ossUser
                password = ossPassword
            }
        }
    }
}

signing {
    //sign(publishing.publications)

    publishing.publications.forEach { publication ->
        sign(publication)
    }
   // sign(configurations.archives.get().filterNot { it.name.contains("dokka") })
}