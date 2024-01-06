package com.nookure.chat.paper.filter;

import com.google.inject.Inject;
import com.nookure.chat.api.chat.ChatFilter;
import com.nookure.chat.api.chat.ChatFilterData;
import com.nookure.chat.api.config.Config;
import com.nookure.chat.api.config.ConfigurationContainer;
import com.nookure.chat.api.config.partials.filters.MentionsFilterConfig;
import com.nookure.chat.paper.NookureChat;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;

import static com.nookure.chat.paper.utils.MessageUtils.cmp;

@ChatFilterData(
    name = "mentions"
)
public class MentionsFilter extends ChatFilter {
  @Inject
  private ConfigurationContainer<Config> config;

  @Override
  public boolean check(@NotNull Player player, @NotNull String message) {
    return true;
  }

  @Override
  public String modify(@NotNull Player player, @NotNull String message) {
    AtomicReference<String> finalMessage = new AtomicReference<>(message);

    if (NookureChat.VERSION < 16) {
      return message;
    }

    Bukkit.getOnlinePlayers().forEach(onlinePlayer -> {
      if (onlinePlayer.getUniqueId() == player.getUniqueId()) {
        return;
      }

      if (message.contains(onlinePlayer.getName())) {
        finalMessage.set(finalMessage.get().replace(
            onlinePlayer.getName(),
            config.get().filters.mentions.getNameReplacement().replace("{player}", onlinePlayer.getName())
        ));

        MentionsFilterConfig mentions = config.get().filters.mentions;
        Title title = Title.title(
            cmp(mentions.getTitle().replace("{player}", player.getName())),
            cmp(mentions.getSubtitle().replace("{player}", player.getName()))
        );

        onlinePlayer.showTitle(title);

        onlinePlayer.playSound(
            onlinePlayer.getLocation(),
            mentions.sound.getSound(),
            mentions.sound.getVolume(),
            mentions.sound.getPitch()
        );
      }
    });

    return finalMessage.get();
  }
}
