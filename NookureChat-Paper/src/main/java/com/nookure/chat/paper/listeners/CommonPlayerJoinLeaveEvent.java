package com.nookure.chat.paper.listeners;

import com.google.inject.Inject;
import com.nookure.chat.api.Logger;
import com.nookure.chat.api.adapters.PermissionAdapter;
import com.nookure.chat.api.config.ConfigurationContainer;
import com.nookure.chat.api.config.FormatConfig;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class CommonPlayerJoinLeaveEvent {
  @Inject
  private ConfigurationContainer<FormatConfig> formatConfig;
  @Inject
  private PermissionAdapter permissionAdapter;
  @Inject
  private Logger logger;

  public Response format(@NotNull Player player) {
    String group = permissionAdapter.getHighestGroup(player);

    String prefix = formatConfig.get().getDefaultPrefix();
    String suffix = formatConfig.get().getDefaultSuffix();
    String joinMessage = formatConfig.get().getDefaultJoinMessage();
    String quitMessage = formatConfig.get().getDefaultQuitMessage();
    String joinTitleMessage = formatConfig.get().getDefaultTitleJoinMessage();
    String joinSubtitleMessage = formatConfig.get().getDefaultSubtitleJoinMessage();
    String firstJoinMessage = formatConfig.get().getDefaultJoinMessage();

    logger.debug("User %s with group %s joined the server", player.getName(), group);

    if (formatConfig.get().getGroups().containsKey(group)) {
      FormatConfig.Group groupConfig = formatConfig.get().getGroups().get(group);

      prefix = groupConfig.getPrefix();
      suffix = groupConfig.getSuffix();
      joinMessage = groupConfig.getJoinMessage().replace("{default}", joinMessage);
      quitMessage = groupConfig.getQuitMessage().replace("{default}", quitMessage);
      joinTitleMessage = groupConfig.getTitleJoinMessage().replace("{default}", joinTitleMessage);
      joinSubtitleMessage = groupConfig.getSubtitleJoinMessage().replace("{default}", joinSubtitleMessage);
    }

    joinMessage = joinMessage
        .replace("{displayname}", MiniMessage.miniMessage().serialize(player.displayName()))
        .replace("{prefix}", prefix)
        .replace("{suffix}", suffix);

    quitMessage = quitMessage
        .replace("{displayname}", MiniMessage.miniMessage().serialize(player.displayName()))
        .replace("{prefix}", prefix)
        .replace("{suffix}", suffix);

    joinTitleMessage = joinTitleMessage
        .replace("{displayname}", MiniMessage.miniMessage().serialize(player.displayName()))
        .replace("{prefix}", prefix)
        .replace("{suffix}", suffix);

    joinSubtitleMessage = joinSubtitleMessage
        .replace("{displayname}", MiniMessage.miniMessage().serialize(player.displayName()))
        .replace("{prefix}", prefix)
        .replace("{suffix}", suffix);

    firstJoinMessage = firstJoinMessage
        .replace("{displayname}", MiniMessage.miniMessage().serialize(player.displayName()))
        .replace("{prefix}", prefix)
        .replace("{suffix}", suffix);

    return new Response(
        MiniMessage.miniMessage().deserialize(joinMessage),
        MiniMessage.miniMessage().deserialize(quitMessage),
        MiniMessage.miniMessage().deserialize(joinTitleMessage),
        MiniMessage.miniMessage().deserialize(joinSubtitleMessage),
        MiniMessage.miniMessage().deserialize(firstJoinMessage)
    );
  }

  public record Response(Component joinMessage, Component quitMessage, Component titleJoinMessage,
                         Component subtitleJoinMessage, Component fistJoinMessage) {

  }
}
