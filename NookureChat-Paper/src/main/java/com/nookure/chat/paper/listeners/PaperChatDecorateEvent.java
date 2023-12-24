package com.nookure.chat.paper.listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PaperChatDecorateEvent extends CommonChatEvent implements Listener {
  @EventHandler
  public void onPlayerChat(AsyncChatEvent event) {
    String stripped = PlainTextComponentSerializer.plainText().serialize(event.message());

    event.setCancelled(true);

    if (!check(event.getPlayer(), stripped)) {
      return;
    }

    Component formatted = format(event.getPlayer(), stripped);

    event.viewers().forEach(viewer -> {
      viewer.sendMessage(formatted);
    });
  }
}
