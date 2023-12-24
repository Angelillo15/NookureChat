package com.nookure.chat.paper;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.nookure.chat.api.config.Config;
import com.nookure.chat.api.managers.FilterManager;
import com.nookure.chat.paper.bootstrap.ChatBootstrapper;
import com.nookure.chat.paper.filter.FloodFilter;
import com.nookure.chat.paper.listeners.PaperChatDecorateEvent;
import org.bukkit.Bukkit;
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
  private Config config;

  public void onEnable() {
    loadListeners();
    registerFilters();
  }

  public void onDisable() {

  }

  public void onReload() {
    // Unregister all listeners
    HandlerList.getHandlerLists().forEach(listener -> {
      listeners.forEach(listener::unregister);
    });

    listeners.clear();
  }

  public void loadListeners() {
    if (VERSION >= 17) {
      registerListener(PaperChatDecorateEvent.class);
    }
  }

  public void registerFilters() {
    filterManager.registerFilter(injector.getInstance(FloodFilter.class), config.flood);
  }

  public void registerListener(Class<? extends Listener> listener) {
    Listener listenerObj = injector.getInstance(listener);
    listeners.add(listenerObj);

    Bukkit.getPluginManager().registerEvents(listenerObj, plugin);
  }
}
