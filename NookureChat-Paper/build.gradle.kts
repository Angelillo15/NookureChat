plugins {
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

dependencies {
    compileOnly(project(":NookureChat-API"))
    compileOnly(libs.paperApi)
    compileOnly(libs.bundles.adventure)
    compileOnly(libs.vault)
}

bukkit {
    name = "NookureChat"
    main = "com.nookure.chat.paper.bootstrap.ChatBootstrapper"
    apiVersion = "1.13"
    version = rootProject.version.toString()
    description = "A chat plugin for Minecraft"
    website = "https://nookure.com"
    authors = listOf("Angelillo15", "Nookure Contributors")
}