package com.nookure.chat.paper.listeners;

import com.google.inject.Inject;
import com.nookure.chat.api.Logger;
import com.nookure.chat.api.TextUtils;
import com.nookure.chat.api.config.ConfigurationContainer;
import com.nookure.chat.api.config.FormatConfig;
import com.nookure.chat.api.config.JoinMotdConfig;
import com.nookure.chat.paper.NookureChat;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static com.nookure.chat.paper.utils.MessageUtils.sendMessage;

@SuppressWarnings("deprecation")
public class PlayerJoinLeaveEvent extends CommonPlayerJoinLeaveEvent implements Listener {
  @Inject
  private ConfigurationContainer<FormatConfig> formatConfig;
  @Inject
  private ConfigurationContainer<JoinMotdConfig> joinMotdConfig;
  @Inject
  private Logger logger;

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    CommonPlayerJoinLeaveEvent.Response format = format(event.getPlayer());

    if (!event.getPlayer().hasPlayedBefore() && !PlainTextComponentSerializer.plainText().serialize(format.fistJoinMessage()).isEmpty()) {
      event.joinMessage(format.fistJoinMessage());
    } else {
      event.joinMessage(format.joinMessage());
    }

    if (formatConfig.get().isEnableJoinTitles()) {
      Player player = event.getPlayer();

      Title title = Title.title(
          format.titleJoinMessage(),
          format.subtitleJoinMessage()
      );

      logger.debug("Sending title to %s: %s", player.getName(), title);
      player.showTitle(title);
    }

    if (joinMotdConfig.get().isEnabled()) {
      sendMessage(event.getPlayer(), TextUtils.processPlaceholders(event.getPlayer(), joinMotdConfig.get().getMotd()));
    }

    logger.debug("Player %s joined the server with join message %s", event.getPlayer().getName(), format.joinMessage());
  }

  @EventHandler
  public void onPlayerLeave(PlayerQuitEvent event) {
    event.quitMessage(format(event.getPlayer()).quitMessage());
  }
}
