package com.nookure.chat.paper;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.nookure.chat.api.Logger;
import com.nookure.chat.api.config.*;
import com.nookure.chat.api.managers.FilterManager;
import com.nookure.chat.paper.bootstrap.ChatBootstrapper;
import com.nookure.chat.paper.cmd.ClearChatCMD;
import com.nookure.chat.paper.cmd.MuteChatCMD;
import com.nookure.chat.paper.cmd.NookureChatCMD;
import com.nookure.chat.paper.filter.*;
import com.nookure.chat.paper.listeners.PaperChatDecorateEvent;
import com.nookure.chat.paper.listeners.PlayerJoinLeaveEvent;
import com.nookure.chat.paper.tasks.BroadcastTask;
import org.bstats.bukkit.Metrics;
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
  private ConfigurationContainer<BannedWordsConfig> bannedWordsConfig;
  @Inject
  private ConfigurationContainer<AutoBroadcastConfig> broadcastConfig;
  @Inject
  private ConfigurationContainer<JoinMotdConfig> joinMotdConfig;
  @Inject
  private CommandMap commandMap;
  @Inject
  private Logger logger;

  public void onEnable() {
    new Metrics(plugin, 21151);
    loadListeners();
    registerFilters();
    loadCommands();
    loadTasks();
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
    registerListener(PaperChatDecorateEvent.class);

    if (formatConfig.get().isEnableJoinQuitMessages()) {
      if (VERSION >= 16) registerListener(PlayerJoinLeaveEvent.class);
    }
  }

  public void loadTasks() {
    logger.debug("Loading tasks...");
    if (broadcastConfig.get().isEnabled()) {
      Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, injector.getInstance(BroadcastTask.class), 0, 20);
    }
  }

  public void reloadConfig() {
    config.reload().join();
    formatConfig.reload().join();
    bannedWordsConfig.reload().join();
    broadcastConfig.reload().join();
    joinMotdConfig.reload().join();
  }

  public void loadCommands() {
    logger.debug("Loading commands...");
    commandMap.register("nchat", injector.getInstance(NookureChatCMD.class));
    commandMap.register("nchat", injector.getInstance(MuteChatCMD.class));
    if (config.get().clearChat.isEnabled())
      commandMap.register("nchat", injector.getInstance(ClearChatCMD.class));

  }

  public void registerFilters() {
    filterManager.registerFilter(injector.getInstance(FloodFilter.class), config.get().filters.flood);
    filterManager.registerFilter(injector.getInstance(SpamFilter.class), config.get().filters.spam);
    filterManager.registerFilter(injector.getInstance(MessageSpamFilter.class), config.get().filters.messageSpam);
    filterManager.registerFilter(injector.getInstance(BannedWordsFilter.class), config.get().filters.bannedWords);
    filterManager.registerFilter(injector.getInstance(MentionsFilter.class), config.get().filters.mentions);
    filterManager.registerFilter(injector.getInstance(RepeatedMessageFilter.class), config.get().filters.repeatedMessage);
    filterManager.registerFilter(injector.getInstance(CapitalizationFilter.class), config.get().filters.capitalization);
  }

  public void registerListener(Class<? extends Listener> listener) {
    Listener listenerObj = injector.getInstance(listener);
    listeners.add(listenerObj);

    Bukkit.getPluginManager().registerEvents(listenerObj, plugin);
  }
}
