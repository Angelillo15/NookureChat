package com.nookure.chat.api;

import com.google.inject.Injector;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.InputStream;

public interface NookureChat {
  int VERSION = Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].split("\\.")[1]);

  Logger getPLogger();

  boolean isDebug();

  void setDebug(boolean debug);

  void reload();

  File getPluginDataFolder();

  InputStream getPluginResource(String s);

  Injector getInjector();
}
