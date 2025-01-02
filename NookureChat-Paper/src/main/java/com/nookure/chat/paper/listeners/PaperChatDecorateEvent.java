package com.nookure.chat.paper.listeners;

import com.google.inject.Inject;
import com.nookure.chat.api.TextUtils;
import com.nookure.chat.api.annotation.MuteChatActive;
import com.nookure.chat.api.config.Config;
import com.nookure.chat.api.config.ConfigurationContainer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.concurrent.atomic.AtomicBoolean;

public class PaperChatDecorateEvent extends CommonChatEvent implements Listener {
  @Inject
  @MuteChatActive
  private AtomicBoolean muteChatActive;
  @Inject
  public ConfigurationContainer<Config> config;

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onPlayerChat(AsyncChatEvent event) {
    if (event.isCancelled()) {
      return;
    }

    String stripped = PlainTextComponentSerializer.plainText().serialize(event.message());

    event.setCancelled(true);

    if (muteChatActive.get() && !event.getPlayer().hasPermission("nookurechat.bypass.mutechat")) {
      TextUtils.sendMessage(event.getPlayer(), config.get().messages.youCantTalk);
      return;
    }

    if (!check(event.getPlayer(), stripped)) {
      return;
    }

    Component formatted = format(event.getPlayer(), stripped);

    event.viewers().forEach(viewer -> {
      viewer.sendMessage(formatted);
    });
  }
}
