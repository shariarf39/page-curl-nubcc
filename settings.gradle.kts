pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    // Set to prefer settings repositories, or you can use FAIL_ON_PROJECT_REPOS
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        // Add JitPack repository
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "page Curl Nub"
include(":app")
