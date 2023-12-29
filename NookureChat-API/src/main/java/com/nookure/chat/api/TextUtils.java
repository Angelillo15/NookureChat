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
}
