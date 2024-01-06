package com.nookure.chat.paper.listeners;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
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

    String stripped = event.getMessage();

    event.setCancelled(!check(event.getPlayer(), stripped));

    event.setFormat(LegacyComponentSerializer.legacy('§').serialize(format(event.getPlayer(), stripped)));
  }
}
