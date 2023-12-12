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
        //https://stackoverflow.com/questions/50389211/android-studio-maven-url-https-jitpack-io-cant-download
        maven { url = uri("https://www.jitpack.io" ) }
    }
}

rootProject.name = "Just_Task"
include(":app")
 