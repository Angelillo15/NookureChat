package com.nookure.chat.paper.listeners;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class BukkitPlayerChatEvent extends CommonChatEvent implements Listener {
  @SuppressWarnings("deprecation")
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPlayerChat(AsyncPlayerChatEvent event) {
    if (event.isCancelled()) {
      return;
    }

    event.setCancelled(true);

    String stripped = event.getMessage();

    if (!check(event.getPlayer(), stripped)) {
      return;
    }

    String finalMessage = LegacyComponentSerializer.legacy('§').serialize(format(event.getPlayer(), stripped));

    event.getRecipients().forEach(player -> player.sendMessage(finalMessage));

    Bukkit.getConsoleSender().sendMessage(finalMessage);
  }
}
