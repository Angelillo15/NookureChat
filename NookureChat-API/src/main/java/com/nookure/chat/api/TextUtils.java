package com.nookure.chat.api;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TextUtils {
  private static final boolean isPlaceholderAPI;
  private static final Pattern hexPattern = Pattern.compile("&#([A-Fa-f0-9]{6})");

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

    return str.replace("§", "&")
      .replace("&0", "<black>")
      .replace("&1", "<dark_blue>")
      .replace("&2", "<dark_green>")
      .replace("&3", "<dark_aqua>")
      .replace("&4", "<dark_red>")
      .replace("&5", "<dark_purple>")
      .replace("&6", "<gold>")
      .replace("&7", "<grey>")
      .replace("&8", "<dark_grey>")
      .replace("&9", "<blue>")
      .replace("&a", "<green>")
      .replace("&b", "<aqua>")
      .replace("&c", "<red>")
      .replace("&d", "<light_purple>")
      .replace("&e", "<yellow>")
      .replace("&f", "<white>")
      .replace("&k", "<obf>")
      .replace("&l", "<b>")
      .replace("&m", "<st>")
      .replace("&n", "<u>")
      .replace("&o", "<i>")
      .replace("&r", "<reset>");
  }
}