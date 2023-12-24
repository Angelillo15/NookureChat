plugins {
  id("java")
  id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.nookure.chat"
version = "1.0.0"

dependencies {
  // Implements projects
  implementation(project(":NookureChat-API"))
  implementation(project(":NookureChat-Paper"))
  // Implements libraries
  implementation(libs.guice)
  implementation(libs.configurateYaml)
  implementation(libs.bundles.adventure)
}

allprojects {
  apply(plugin = "com.github.johnrengelman.shadow")
  apply(plugin = "java")

  repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://papermc.io/repo/repository/maven-releases/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
  }

  tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
  }

  java {
    toolchain {
      languageVersion.set(JavaLanguageVersion.of(17))
    }
  }

  dependencies {
    compileOnly(rootProject.libs.guice)
    compileOnly(rootProject.libs.adventureApi)
  }
}