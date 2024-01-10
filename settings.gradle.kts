pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    // app/build.gradle.kts
// settings.gradle.kts
    pluginManagement {
        repositories {
            maven("https://jitpack.io")
        }
    }
    dependencyResolutionManagement {
        repositories {
            maven("https://jitpack.io")
        }
    }
}

rootProject.name = "Doan_3tuan"
include(":app")
