package com.nookure.chat.paper.filter;

import com.google.inject.Inject;
import com.nookure.chat.api.chat.ChatFilter;
import com.nookure.chat.api.chat.ChatFilterData;
import com.nookure.chat.api.chat.ChatFilterPriority;
import com.nookure.chat.api.config.Config;
import com.nookure.chat.api.config.ConfigurationContainer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

import static com.nookure.chat.paper.utils.MessageUtils.sendMessage;

@ChatFilterData(
    name = "messageSpam",
    permission = "nookurechat.bypass.filter.messageSpam",
    priority = ChatFilterPriority.LATEST
)
public class MessageSpamFilter extends ChatFilter {
  @Inject
  private ConfigurationContainer<Config> config;
  private final HashMap<UUID, Long> lastMessage = new HashMap<>();
  @Override
  public boolean check(@NotNull Player player, @NotNull String message) {
    if (!lastMessage.containsKey(player.getUniqueId())) {
      lastMessage.put(player.getUniqueId(), System.currentTimeMillis());
      return true;
    }

    long lastMessageTime = lastMessage.get(player.getUniqueId());
    long currentTime = System.currentTimeMillis();
    long difference = currentTime - lastMessageTime;

    if (difference < config.get().filters.messageSpam.getCooldown() * 1000L) {
      sendMessage(player, config.get().filters.messageSpam.getDenyMessage().replace(
          "{time}", String.valueOf(config.get().filters.messageSpam.getCooldown() - (difference / 1000)))
      );
      return false;
    }

    lastMessage.put(player.getUniqueId(), currentTime);
    return true;
  }
}
