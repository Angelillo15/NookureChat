package com.nookure.chat.api;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class TextUtils {
  private static final boolean isPlaceholderAPI;

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
        return PlaceholderAPI.setPlaceholders(player, message);
      }
    } catch (Exception e) {
      return message;
    }

    return message;
  }

  public static String toMM(String str) {
    if (!str.contains("&") && !str.contains("§")) return str;

    return str
        .replace("§", "&")
        .replace("&0", "<reset><black>")
        .replace("&1", "<reset><dark_blue>")
        .replace("&2", "<reset><dark_green>")
        .replace("&3", "<reset><dark_aqua>")
        .replace("&4", "<reset><dark_red>")
        .replace("&5", "<reset><dark_purple>")
        .replace("&6", "<reset><gold>")
        .replace("&7", "<reset><grey>")
        .replace("&8", "<reset><dark_grey>")
        .replace("&9", "<reset><blue>")
        .replace("&a", "<reset><green>")
        .replace("&b", "<reset><aqua>")
        .replace("&c", "<reset><red>")
        .replace("&d", "<reset><light_purple>")
        .replace("&e", "<reset><yellow>")
        .replace("&f", "<reset><white>")
        .replace("&k", "<obf>")
        .replace("&l", "<b>")
        .replace("&m", "<st>")
        .replace("&n", "<u>")
        .replace("&r", "<reset>")
        .replace("&o", "<i>");
  }
}
