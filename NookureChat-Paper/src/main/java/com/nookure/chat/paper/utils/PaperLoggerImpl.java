package com.nookure.chat.paper.utils;

import com.nookure.chat.api.Logger;
import com.nookure.chat.paper.bootstrap.ChatBootstrapper;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class PaperLoggerImpl implements Logger {
  private final BukkitAudiences audiences;
  private final ChatBootstrapper plugin;

  public PaperLoggerImpl(BukkitAudiences audiences, ChatBootstrapper plugin) {
    this.audiences = audiences;
    this.plugin = plugin;
  }

  @Override
  public void info(Component component) {
    audiences.console().sendMessage(getDefaultStyle(component, NamedTextColor.GRAY, "INFO"));
  }

  @Override
  public void warning(Component component) {
    audiences.console().sendMessage(getDefaultStyle(component, NamedTextColor.YELLOW, "WARN"));
  }

  @Override
  public void severe(Component component) {
    audiences.console().sendMessage(getDefaultStyle(component, NamedTextColor.RED, "SEVERE"));
  }

  @Override
  public void debug(Component component) {
    if (!plugin.isDebug()) return;
    audiences.console().sendMessage(getDefaultStyle(component, NamedTextColor.GRAY, "DEBUG"));
  }

  private Component getDefaultStyle(Component component, NamedTextColor color, String mode) {
    return Component
        .text("NookureChat")
        .color(NamedTextColor.RED)
        .append(Component.text(" | ").color(NamedTextColor.RED))
        .append(Component.text(mode).color(color))
        .append(Component.text(" > ").color(NamedTextColor.RED))
        .append(component);
  }
}
