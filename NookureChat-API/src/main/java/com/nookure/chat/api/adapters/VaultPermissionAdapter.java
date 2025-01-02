package com.nookure.chat.api.adapters;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultPermissionAdapter extends PermissionAdapter {
  private final Permission permission;
  public VaultPermissionAdapter() {
    this.permission = setupPermissions();
  }
  @Override
  public String getHighestGroup(Player player) {
    return permission.getPrimaryGroup(player);
  }

  private Permission setupPermissions() {
    RegisteredServiceProvider<Permission> rsp = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
    return rsp != null ? rsp.getProvider() : null;
  }
}
