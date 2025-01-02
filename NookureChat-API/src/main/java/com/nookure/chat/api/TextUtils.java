package com.nookure.chat.api;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {
  private static final boolean isPlaceholderAPI;
  private static final Pattern hexPattern = Pattern.compile("&#([A-Fa-f0-9]{6})");
  public static final MiniMessage CHAT_FORMATTER = MiniMessage.builder()
      .tags(TagResolver.builder()
          .resolvers(
              StandardTags.color(),
              StandardTags.decorations(),
              StandardTags.rainbow(),
              StandardTags.gradient()
          )
          .build())
      .build();

  static {
    boolean isPlaceholderAPITemp;
    try {
      Class.forName("me.clip.placeholderapi.PlaceholderAPI");
      isPlaceholderAPITemp = true;
    } catch (ClassNotFoundException e) {
      isPlaceholderAPITemp = false;
    }
    isPlaceholderAPI = isPlaceholderAPITemp;
  }

  public static String processPlaceholders(Player player, String message) {
    try {
      if (isPlaceholderAPI) {
        return toMM(PlaceholderAPI.setPlaceholders(player, message));
      }
    } catch (Exception e) {
      return message;
    }
    return message;
  }

  public static String toMM(@NotNull String str) {
    str = str.replace("&", "§")
        .replace("§0", "<black>")
        .replace("§1", "<dark_blue>")
        .replace("§2", "<dark_green>")
        .replace("§3", "<dark_aqua>")
        .replace("§4", "<dark_red>")
        .replace("§5", "<dark_purple>")
        .replace("§6", "<gold>")
        .replace("§7", "<grey>")
        .replace("§8", "<dark_grey>")
        .replace("§9", "<blue>")
        .replace("§a", "<green>")
        .replace("§b", "<aqua>")
        .replace("§c", "<red>")
        .replace("§d", "<light_purple>")
        .replace("§e", "<yellow>")
        .replace("§f", "<white>")
        .replace("§k", "<obf>")
        .replace("§l", "<b>")
        .replace("§m", "<st>")
        .replace("§n", "<u>")
        .replace("§o", "<i>")
        .replace("§r", "<reset>");

    if (str.contains("#")) {
      Matcher matcher = hexPattern.matcher(str);
      StringBuilder buffer = new StringBuilder();
      while (matcher.find()) {
        String replacement = String.format("<#%s>", matcher.group(1));
        matcher.appendReplacement(buffer, replacement);
      }
      matcher.appendTail(buffer);
      str = buffer.toString();
    }

    return str;
  }

  public static void sendMessage(@NotNull CommandSender player, @NotNull String message) {
    if (message.isEmpty()) {
      return;
    }

    player.sendMessage(MiniMessage.miniMessage().deserialize(toMM(message)));
  }

  public static void broadcastMessage(@NotNull String message) {
    if (message.isEmpty()) {
      return;
    }

    for (Player player : Bukkit.getOnlinePlayers()) {
      player.sendMessage(MiniMessage.miniMessage().deserialize(toMM(message)));
    }
  }
}