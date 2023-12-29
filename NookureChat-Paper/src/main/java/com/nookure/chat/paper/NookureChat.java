package com.nookure.chat.paper;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.nookure.chat.api.Logger;
import com.nookure.chat.api.config.Config;
import com.nookure.chat.api.config.ConfigurationContainer;
import com.nookure.chat.api.config.FormatConfig;
import com.nookure.chat.api.managers.FilterManager;
import com.nookure.chat.paper.bootstrap.ChatBootstrapper;
import com.nookure.chat.paper.cmd.NookureChatCMD;
import com.nookure.chat.paper.filter.BannedWordsFilter;
import com.nookure.chat.paper.filter.FloodFilter;
import com.nookure.chat.paper.filter.MessageSpamFilter;
import com.nookure.chat.paper.filter.SpamFilter;
import com.nookure.chat.paper.listeners.PaperChatDecorateEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class NookureChat {
  public static final int VERSION = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].split("\\.")[1]);
  private final ArrayList<Listener> listeners = new ArrayList<>();
  @Inject
  private FilterManager filterManager;
  @Inject
  private ChatBootstrapper plugin;
  @Inject
  private Injector injector;
  @Inject
  private ConfigurationContainer<Config> config;
  @Inject
  private ConfigurationContainer<FormatConfig> formatConfig;
  @Inject
  private CommandMap commandMap;
  @Inject
  private Logger logger;

  public void onEnable() {
    loadListeners();
    registerFilters();
    loadCommands();
  }

  public void onDisable() {

  }

  public void onReload() {
    // Unregister all listeners
    HandlerList.getHandlerLists().forEach(listener -> {
      listeners.forEach(listener::unregister);
    });

    listeners.clear();
    reloadConfig();
    loadListeners();
  }

  public void loadListeners() {
    logger.debug("Loading listeners...");
    if (VERSION >= 17) {
      registerListener(PaperChatDecorateEvent.class);
    }
  }

  public void reloadConfig() {
    config.reload().join();
    formatConfig.reload().join();
  }

  public void loadCommands() {
    logger.debug("Loading commands...");
    commandMap.register("nchat", injector.getInstance(NookureChatCMD.class));
  }

  public void registerFilters() {
    filterManager.registerFilter(injector.getInstance(FloodFilter.class), config.get().filters.flood);
    filterManager.registerFilter(injector.getInstance(SpamFilter.class), config.get().filters.spam);
    filterManager.registerFilter(injector.getInstance(MessageSpamFilter.class), config.get().filters.messageSpam);
    filterManager.registerFilter(injector.getInstance(BannedWordsFilter.class), config.get().filters.bannedWords);
  }

  public void registerListener(Class<? extends Listener> listener) {
    Listener listenerObj = injector.getInstance(listener);
    listeners.add(listenerObj);

    Bukkit.getPluginManager().registerEvents(listenerObj, plugin);
  }
}
