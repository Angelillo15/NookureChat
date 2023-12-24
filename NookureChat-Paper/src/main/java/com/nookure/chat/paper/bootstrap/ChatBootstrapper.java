package com.nookure.chat.paper.bootstrap;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.nookure.chat.Constants;
import com.nookure.chat.api.Logger;
import com.nookure.chat.api.NookureChatPlatform;
import com.nookure.chat.api.config.Config;
import com.nookure.chat.api.config.ConfigMapper;
import com.nookure.chat.api.config.ConfigurationContainer;
import com.nookure.chat.api.config.FormatConfig;
import com.nookure.chat.paper.NookureChat;
import com.nookure.chat.paper.utils.PaperLoggerImpl;
import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ChatBootstrapper extends JavaPlugin implements NookureChatPlatform<JavaPlugin> {
  public static final int version = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].split("\\.")[1]);
  private BukkitAudiences audiences;
  private NookureChat plugin;
  private Logger logger;
  private boolean debug = false;
  private Injector injector;
  private ConfigurationContainer<Config> config;
  private ConfigurationContainer<FormatConfig> formatConfig;
  private final ConfigMapper configMapper = new ConfigMapper();

  @Override
  public void onEnable() {
    // Load plugin logger and audiences
    loadLogger();
    // Print plugin header
    audiences.console().sendMessage(Component.text(
        "▄             ▄ •▄ ▄• ▄▌▄▄▄  ▄▄▄ .     ▄▄·  ▄ .▄ ▄▄▄· ▄▄▄▄▄").color(NamedTextColor.RED)
    );
    audiences.console().sendMessage(Component.text(
        "•█▌▐█▪     ▪     █▌▄▌▪█▪██▌▀▄ █·▀▄.▀·    ▐█ ▌▪██▪▐█▐█ ▀█ •██ \s").color(NamedTextColor.RED)
    );
    audiences.console().sendMessage(Component.text(
        "▐█▐▐▌ ▄█▀▄  ▄█▀▄ ▐▀▀▄·█▌▐█▌▐▀▀▄ ▐▀▀▪▄    ██ ▄▄██▀▐█▄█▀▀█  ▐█.▪").color(NamedTextColor.RED)
    );
    audiences.console().sendMessage(Component.text(
        "██▐█▌▐█▌.▐▌▐█▌.▐▌▐█.█▌▐█▄█▌▐█•█▌▐█▄▄▌    ▐███▌██▌▐▀▐█ ▪▐▌ ▐█▌·").color(NamedTextColor.RED)
    );
    audiences.console().sendMessage(Component.text(
        "▀▀ █▪ ▀█▄▀▪ ▀█▄▀▪·▀  ▀ ▀▀▀ .▀  ▀ ▀▀▀     ·▀▀▀ ▀▀▀ · ▀  ▀  ▀▀▀\s").color(NamedTextColor.RED)
    );
    audiences.console().sendMessage(Component.text(
        "NookureChat v" + Constants.VERSION + " by Angelillo15").color(NamedTextColor.RED));
    // Load configs
    loadConfig();
    // Load Google Guice injector
    loadInjector();
    // Inject the main class of the plugin
    injectPlugin();
    // Load the plugin config
    loadConfig();
    // Do the plugin enable
    plugin.onEnable();
    logger.info("Plugin enabled!");
  }

  public void loadConfig() {
    try {
      config = ConfigurationContainer.load(getDataFolder().toPath(), Config.class);
      formatConfig = ConfigurationContainer.load(getDataFolder().toPath(), FormatConfig.class, "format.yml");

      configMapper.register(Config.class, config);
      configMapper.register(FormatConfig.class, formatConfig);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void onDisable() {
    logger.info("Disabling plugin...");
    plugin.onDisable();
    logger.info("Plugin disabled!");
  }

  public void loadLogger() {
    audiences = BukkitAudiences.create(this);
    logger = new PaperLoggerImpl(audiences, this);
  }

  public void injectPlugin() {
    logger.info("💉  Injecting plugin...");
    plugin = injector.getInstance(NookureChat.class);
    logger.info("💉  Plugin injected!");
  }

  public void loadInjector() {
    injector = Guice.createInjector(new PluginModule(this));
  }

  @Override
  public Logger getPLogger() {
    return logger;
  }

  @Override
  public boolean isDebug() {
    return debug;
  }

  @Override
  public void setDebug(boolean debug) {
    this.debug = debug;
  }

  @Override
  public File getPluginDataFolder() {
    return getDataFolder();
  }

  @Override
  public InputStream getPluginResource(String s) {
    return getResource(s);
  }

  @Override
  public Injector getInjector() {
    return injector;
  }

  @Override
  public JavaPlugin getPlatform() {
    return this;
  }

  public AudienceProvider getAudiences() {
    return audiences;
  }

  public ConfigMapper getConfigMapper() {
    return configMapper;
  }

  public ConfigurationContainer<Config> getPluginConfig() {
    return config;
  }

  public ConfigurationContainer<FormatConfig> getFormatConfig() {
    return formatConfig;
  }

  public static ChatBootstrapper getPlugin() {
    return getPlugin(ChatBootstrapper.class);
  }
}
