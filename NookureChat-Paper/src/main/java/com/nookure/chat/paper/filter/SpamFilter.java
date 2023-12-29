package com.nookure.chat.paper.filter;

import com.google.inject.Inject;
import com.nookure.chat.api.chat.ChatFilter;
import com.nookure.chat.api.chat.ChatFilterData;
import com.nookure.chat.api.config.Config;
import com.nookure.chat.api.config.ConfigurationContainer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

import static com.nookure.chat.paper.utils.MessageUtils.sendMessage;

@ChatFilterData(
    name = "spam",
    permission = "nookurechat.bypass.filter.spam"
)
public class SpamFilter extends ChatFilter {
  @Inject
  private ConfigurationContainer<Config> config;
  public static final Pattern IPV4_REGEX = Pattern.compile("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$");
  public static final Pattern DOMAIN_REGEX = Pattern.compile("(https://www\\.|//mc\\.|//play\\.|http://www\\.|https://|http://)?[a-zA-Z0-9]{2,}(\\.[a-zA-Z0-9]{2,})(\\.[a-zA-Z0-9]{2,})?");
  @Override
  public boolean check(@NotNull Player player, @NotNull String message) {
    if (IPV4_REGEX.matcher(message).find() || DOMAIN_REGEX.matcher(message).find()) {
      sendMessage(player, config.get().filters.spam.getDenyMessage());
      return false;
    }

    return true;
  }
}
