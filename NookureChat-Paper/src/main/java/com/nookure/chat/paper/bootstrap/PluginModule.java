package com.nookure.chat.paper.bootstrap;

import com.google.inject.AbstractModule;
import com.nookure.chat.api.Logger;
import com.nookure.chat.api.NookureChat;
import com.nookure.chat.api.adapters.NoImplPermissionAdapter;
import com.nookure.chat.api.adapters.PermissionAdapter;
import com.nookure.chat.api.adapters.VaultPermissionAdapter;
import com.nookure.chat.api.config.Config;
import com.nookure.chat.api.config.ConfigMapper;
import com.nookure.chat.api.config.FormatConfig;
import com.nookure.chat.api.managers.FilterManager;
import net.kyori.adventure.platform.AudienceProvider;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginModule extends AbstractModule {
  private final ChatBootstrapper plugin;

  public PluginModule(
      ChatBootstrapper plugin
  ) {
    this.plugin = plugin;
  }

  @Override
  protected void configure() {
    bind(JavaPlugin.class).toInstance(plugin);
    bind(Logger.class).toInstance(plugin.getPLogger());
    bind(ChatBootstrapper.class).toInstance(plugin);
    bind(NookureChat.class).toInstance(plugin);
    bind(AudienceProvider.class).toInstance(plugin.getAudiences());
    bind(ConfigMapper.class).toInstance(plugin.getConfigMapper());
    bind(Config.class).toInstance(plugin.getPluginConfig().get());
    bind(FormatConfig.class).toInstance(plugin.getFormatConfig().get());
    bind(FilterManager.class).asEagerSingleton();
    bind(PermissionAdapter.class).toInstance(getPermissionAdapter());
    bind(ChatBootstrapper.class).toInstance(plugin);
    bind(CommandMap.class).toInstance(getCommandMap());
  }

  public PermissionAdapter getPermissionAdapter() {
    try {
      Class.forName("net.milkbowl.vault.permission.Permission");
      return new VaultPermissionAdapter(setupPermissions());
    } catch (ClassNotFoundException e) {
      return new NoImplPermissionAdapter();
    }
  }

  private Permission setupPermissions() {
    RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
    return rsp != null ? rsp.getProvider() : null;
  }

  private CommandMap getCommandMap() {
    try {
      return (CommandMap) Bukkit.getServer().getClass().getDeclaredMethod("getCommandMap").invoke(Bukkit.getServer());
    } catch (Exception e) {
      throw new RuntimeException("Could not get CommandMap", e);
    }
  }
}
