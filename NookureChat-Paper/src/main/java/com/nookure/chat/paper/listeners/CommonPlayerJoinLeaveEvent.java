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
    String firstJoinMessage = formatConfig.get().getFirstJoinMessage();

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

    joinMessage = replaceJoinMessage(
        joinMessage,
        player.getName(),
        MiniMessage.miniMessage().serialize(player.displayName()),
        prefix,
        suffix
    );

    quitMessage = replaceJoinMessage(
        quitMessage,
        player.getName(),
        MiniMessage.miniMessage().serialize(player.displayName()),
        prefix,
        suffix
    );

    joinTitleMessage = replaceJoinMessage(
        joinTitleMessage,
        player.getName(),
        MiniMessage.miniMessage().serialize(player.displayName()),
        prefix,
        suffix
    );

    joinSubtitleMessage = replaceJoinMessage(
        joinSubtitleMessage,
        player.getName(),
        MiniMessage.miniMessage().serialize(player.displayName()),
        prefix,
        suffix
    );

    firstJoinMessage = replaceJoinMessage(
        firstJoinMessage,
        player.getName(),
        MiniMessage.miniMessage().serialize(player.displayName()),
        prefix,
        suffix
    );

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

  public String replaceJoinMessage(
      @NotNull String message,
      @NotNull final String name,
      @NotNull final String displayName,
      @NotNull final String prefix,
      @NotNull final String suffix
  ) {
    message = message.replace("{displayname}", displayName);
    message = message.replace("{name}", name);
    message = message.replace("{prefix}", prefix);
    message = message.replace("{suffix}", suffix);

    return message;
  }
}
