package com.nookure.chat.api;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class TextUtils {
  private static final boolean isPlaceholderAPI;

  static {
    try {
      Class.forName("me.clip.placeholderapi.PlaceholderAPI");
      isPlaceholderAPI = true;
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public static String processPlaceholders(Player player, String message) {
    if (isPlaceholderAPI) {
      return PlaceholderAPI.setPlaceholders(player, message);
    }

    return message;
  }
}
