package com.nookure.chat.paper.filter;

import com.google.inject.Inject;
import com.nookure.chat.api.chat.ChatFilter;
import com.nookure.chat.api.chat.ChatFilterData;
import com.nookure.chat.api.config.BannedWordsConfig;
import com.nookure.chat.api.config.Config;
import com.nookure.chat.api.config.ConfigurationContainer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.nookure.chat.paper.utils.MessageUtils.sendMessage;

@ChatFilterData(
    name = "bannedWords",
    permission = "nookurechat.bypass.filter.bannedWords"
)
public class BannedWordsFilter extends ChatFilter {
  @Inject
  private ConfigurationContainer<Config> config;
  @Inject
  private ConfigurationContainer<BannedWordsConfig> bannedWordsConfig;
  @Override
  public boolean check(@NotNull Player player, @NotNull String message) {
    for (String word : bannedWordsConfig.get().getWords()) {
      if (message.toLowerCase().contains(word.toLowerCase())) {
        sendMessage(player, config.get().filters.bannedWords.getDenyMessage());
        return false;
      }
    }

    return true;
  }
}
