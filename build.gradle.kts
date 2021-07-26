allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.2.2")
        classpath(kotlin("gradle-plugin", version = Versions.kotlin))
        classpath(kotlin("android-extensions", version = Versions.kotlin))
        classpath(kotlin("serialization", version = Versions.kotlin))
        classpath(Deps.hiltGradlePlugin)
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}
