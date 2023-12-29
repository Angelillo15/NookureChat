package com.nookure.chat.paper.utils;

import com.nookure.chat.paper.NookureChat;
import com.nookure.chat.paper.bootstrap.ChatBootstrapper;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtils {
  private static final LegacyComponentSerializer serializer = LegacyComponentSerializer
      .builder()
      .character('&')
      .build();

  public static Component cmp(String message) {
    message = message.replace("{prefix}", ChatBootstrapper
        .getPlugin()
        .getPluginConfig()
        .get()
        .getPrefix()
    );

    if (message.contains("&")) {
      return serializer.deserialize(
          MiniMessage.miniMessage().serialize(
              MiniMessage.miniMessage().deserialize(message)
          )
      );
    }

    return MiniMessage.miniMessage().deserialize(message);
  }

  public static void sendMessage(CommandSender sender, String message) {
    if (NookureChat.VERSION >= 17) {
      sender.sendMessage(cmp(message));
    } else {
      BukkitAudiences se = (BukkitAudiences) ChatBootstrapper.getPlugin().getAudiences();
      se.sender(sender).sendMessage(cmp(message));
    }
  }
}
