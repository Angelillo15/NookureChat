plugins {
  id("net.kyori.blossom") version "2.1.0"
}

dependencies {
  compileOnly(libs.paperApi)
  compileOnly(libs.adventureBukkit)
  compileOnly(libs.adventureLegacy)
  compileOnly(libs.adventureApi)
  compileOnly(libs.miniMessage)
  compileOnly(libs.configurateYaml)
  compileOnly(libs.vault)
  compileOnly(libs.placeholderApi)
}

sourceSets {
  main {
    blossom {
      javaSources {
        property("version", rootProject.version.toString())
      }
    }
  }
}