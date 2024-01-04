package com.nookure.chat.paper.tasks;

import com.google.inject.Inject;
import com.nookure.chat.api.TextUtils;
import com.nookure.chat.api.config.AutoBroadcastConfig;
import com.nookure.chat.api.config.ConfigurationContainer;
import com.nookure.chat.api.config.partials.broadcast.BroadcastGroupPartial;
import com.nookure.chat.api.font.DefaultFontInfo;
import org.bukkit.Bukkit;

import java.util.HashMap;

import static com.nookure.chat.paper.utils.MessageUtils.sendMessage;

public class BroadcastTask implements Runnable {
  @Inject
  private ConfigurationContainer<AutoBroadcastConfig> config;
  private final HashMap<BroadcastGroupPartial, Integer> currentMessages = new HashMap<>();
  private static final long maxTick = Long.MAX_VALUE - 1000;
  // One tick per second, not like minecraft ticks
  private long currentTick = 0;

  @Override
  public void run() {
    if (!config.get().isEnabled()) {
      return;
    }

    if (currentTick > maxTick) {
      currentTick = 0;
    }

    currentTick += 1;

    config.get().getBroadcasts().forEach((name, broadcast) -> {
      if (!broadcast.isEnabled()) {
        return;
      }

      if (currentTick % broadcast.getInterval() != 0) {
        return;
      }

      Bukkit.getOnlinePlayers().forEach(player -> {
        if (!broadcast.getHeader().isEmpty()) {
          sendMessage(player, DefaultFontInfo.centerIfContains(broadcast.getHeader()));
        }

        if (currentMessages.containsKey(broadcast)) {
          int size = broadcast.getMessages().size();

          if (currentMessages.get(broadcast) + 1 >= size) {
            currentMessages.put(broadcast, 0);
          } else {
            currentMessages.put(broadcast, currentMessages.get(broadcast) + 1);
          }
        } else {
          currentMessages.put(broadcast, 0);
        }

        String message = broadcast.getMessages().get(currentMessages.get(broadcast));

        message = message.replace("{prefix}", broadcast.getPrefix());

        for (String s : message.split("\n")) {
          sendMessage(player, DefaultFontInfo.centerIfContains(TextUtils.processPlaceholders(player, s)));
        }

        if (!broadcast.getFooter().isEmpty()) {
          sendMessage(player, DefaultFontInfo.centerIfContains(broadcast.getFooter()));
        }

        if (broadcast.getSound().isEnabled()) {
          player.playSound(
              player.getLocation(),
              broadcast.getSound().getSound(),
              broadcast.getSound().getVolume(),
              broadcast.getSound().getPitch()
          );
        }
      });
    });
  }
}
