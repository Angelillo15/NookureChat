package com.nookure.chat.paper.filter;

import com.google.inject.Inject;
import com.nookure.chat.api.chat.ChatFilter;
import com.nookure.chat.api.chat.ChatFilterData;
import com.nookure.chat.api.config.Config;
import com.nookure.chat.api.config.ConfigurationContainer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@ChatFilterData(
    name = "capitalization"
)
public class CapitalizationFilter extends ChatFilter {
  @Inject
  private ConfigurationContainer<Config> config;

  @Override
  public boolean check(@NotNull Player player, @NotNull String message) {
    return true;
  }

  @Override
  public String modify(@NotNull Player player, @NotNull String message) {
    message = message.substring(0, 1).toUpperCase() + message.substring(1).toLowerCase();

    if (config.get().filters.capitalization.addDot()) {
      message = message + ".";
    }

    return message;
  }
}
