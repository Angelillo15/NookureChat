package com.nookure.chat.paper.filter;

import com.google.inject.Inject;
import com.nookure.chat.api.chat.ChatFilter;
import com.nookure.chat.api.chat.ChatFilterData;
import com.nookure.chat.api.config.Config;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

import static com.nookure.chat.paper.utils.MessageUtils.sendMessage;


@ChatFilterData(
    name = "flood",
    permission = "nookurechat.bypass.filter.flood"
)
public class FloodFilter extends ChatFilter {
  @Inject
  private Config config;

  @Override
  public boolean check(@NotNull Player player, @NotNull String message) {
    Pattern matcher = Pattern.compile("(.)\\1{" + config.filters.flood.getMaxRepetitions() + ",}");

    if (matcher.matcher(message).find()) {
      sendMessage(player, config.filters.flood.getDenyMessage());
      return false;
    }

    return true;
  }
}
