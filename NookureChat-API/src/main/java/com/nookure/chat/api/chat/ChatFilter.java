package com.nookure.chat.api.chat;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * The ChatFilter class is used to filter the chat.
 * This class must have the ChatFilterData annotation
 * The ChatFilterData annotation is used to specify the name of the filter,
 * the priority of the filter, and the bypass permission of the filter.
 */
public abstract class ChatFilter {
  /**
   * Check if the message is valid
   * if the message is not valid, return false,
   * and the message will not be sent to the
   * other players.
   * @param player The player who sent the message
   * @param message The message to check
   * @return true if the message is valid, false if the message is invalid
   */
  abstract public boolean check(@NotNull Player player, @NotNull String message);
}
