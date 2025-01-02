package com.nookure.chat.api.adapters;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Objects;

public class LuckPermsPermissionAdaptar extends PermissionAdapter {
  private final RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);

  @Override
  public String getHighestGroup(Player player) {
    if (provider == null) {
      throw new IllegalStateException("LuckPerms is not installed");
    }

    return Objects.requireNonNull(provider.getProvider().getUserManager().getUser(player.getUniqueId())).getPrimaryGroup();
  }
}
