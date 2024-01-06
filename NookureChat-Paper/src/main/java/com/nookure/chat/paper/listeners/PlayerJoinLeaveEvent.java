package com.nookure.chat.paper.listeners;

import com.google.inject.Inject;
import com.nookure.chat.api.config.ConfigurationContainer;
import com.nookure.chat.api.config.FormatConfig;
import com.nookure.chat.paper.NookureChat;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinLeaveEvent extends CommonPlayerJoinLeaveEvent implements Listener {
  @Inject
  private ConfigurationContainer<FormatConfig> formatConfig;

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    CommonPlayerJoinLeaveEvent.Response format = format(event.getPlayer());

    event.joinMessage(format.joinMessage());

    if (formatConfig.get().isEnableJoinTitles() && NookureChat.VERSION >= 16) {
      Player player = event.getPlayer();

      Title title = Title.title(
          format.titleJoinMessage(),
          format.subtitleJoinMessage()
      );

      player.showTitle(title);
    }
  }

  @EventHandler
  public void onPlayerLeave(PlayerQuitEvent event) {
    event.quitMessage(format(event.getPlayer()).quitMessage());
  }
}
