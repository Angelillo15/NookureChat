package com.nookure.chat.paper.listeners;

import com.google.inject.Inject;
import com.nookure.chat.api.TextUtils;
import com.nookure.chat.api.adapters.PermissionAdapter;
import com.nookure.chat.api.config.FormatConfig;
import com.nookure.chat.api.managers.FilterManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicReference;

public abstract class CommonChatEvent {
  @Inject
  private FilterManager filterManager;
  @Inject
  private FormatConfig formatConfig;
  @Inject
  private PermissionAdapter permissionAdapter;

  public boolean check(Player player, String message) {
    for (var filter : filterManager.getFilters().values()) {
      if (!filter.check(player, message)) {
        return false;
      }
    }

    return true;
  }

  public Component format(Player player, String message) {
      String group = permissionAdapter.getHighestGroup(player);

    AtomicReference<String> prefix = new AtomicReference<>(formatConfig.getDefaultPrefix());
    AtomicReference<String> suffix = new AtomicReference<>(formatConfig.getDefaultSuffix());
    AtomicReference<String> format = new AtomicReference<>(formatConfig.getDefaultFormat());

    if (formatConfig.getGroups().containsKey(group)) {
      FormatConfig.Group groupConfig = formatConfig.getGroups().get(group);

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
          .replace("{message}", message)
      );
    }

    format.set(format.get()
        .replace("{prefix}", prefix.get())
        .replace("{suffix}", suffix.get())
    );

    return MiniMessage.miniMessage().deserialize(format.get());
  }
}
