buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}