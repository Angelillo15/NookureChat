package com.nookure.chat.paper.listeners;

import com.google.inject.Inject;
import com.nookure.chat.api.Logger;
import com.nookure.chat.api.TextUtils;
import com.nookure.chat.api.adapters.PermissionAdapter;
import com.nookure.chat.api.config.ConfigurationContainer;
import com.nookure.chat.api.config.FormatConfig;
import com.nookure.chat.api.managers.FilterManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicReference;

public abstract class CommonChatEvent {
  @Inject
  private FilterManager filterManager;
  @Inject
  private ConfigurationContainer<FormatConfig> formatConfig;
  @Inject
  private PermissionAdapter permissionAdapter;
  @Inject
  private Logger logger;

  public boolean check(@NotNull Player player, @NotNull String message) {
    for (var filter : filterManager.getFilters().values()) {
      if (!player.hasPermission(filter.getFilterData().permission()) && !filter.check(player, message)) {
        return false;
      }
    }

    return true;
  }

  public Component format(@NotNull Player player, @NotNull String message) {
    String group = permissionAdapter.getHighestGroup(player);

    AtomicReference<String> prefix = new AtomicReference<>(formatConfig.get().getDefaultPrefix());
    AtomicReference<String> suffix = new AtomicReference<>(formatConfig.get().getDefaultSuffix());
    AtomicReference<String> format = new AtomicReference<>(formatConfig.get().getDefaultFormat());

    logger.debug("User %s with group %s sent a message: %s", player.getName(), group, message);

    if (formatConfig.get().getGroups().containsKey(group)) {
      FormatConfig.Group groupConfig = formatConfig.get().getGroups().get(group);

      prefix.set(groupConfig.getPrefix());
      suffix.set(groupConfig.getSuffix());
      format.set(groupConfig.getFormat().replace("{default}", format.get()));
    }

    format.set(TextUtils.processPlaceholders(player, format.get()));

    if (format.get().contains("{displayname}") && message.contains("{message}")) {
      format.set(format.get()
          .replace("{displayname}", "%s")
          .replace("{message}", "%s")
      );
    } else {
      format.set(format.get().replace("%", "%%"));
      format.set(format.get()
          .replace("{displayname}", MiniMessage.miniMessage().serialize(player.displayName()))
      );
    }

    format.set(format.get()
        .replace("{prefix}", prefix.get())
        .replace("{suffix}", suffix.get())
    );

    if (player.hasPermission("nookurechat.color")) {
      format.set(format.get().replace("{message}", message));
    } else {
      format.set(format.get().replace("{message}", PlainTextComponentSerializer.plainText().serialize(
          MiniMessage.miniMessage().deserialize(message)
      )));
    }

    return MiniMessage.miniMessage().deserialize(format.get());
  }
}
