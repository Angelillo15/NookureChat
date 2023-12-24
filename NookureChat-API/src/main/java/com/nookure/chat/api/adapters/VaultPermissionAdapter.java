package com.nookure.chat.api.adapters;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;

public class VaultPermissionAdapter extends PermissionAdapter {
  private final Permission permission;
  public VaultPermissionAdapter(Permission permission) {
    this.permission = permission;
  }
  @Override
  public String getHighestGroup(Player player) {
    return permission.getPrimaryGroup(player);
  }

  @Override
  public String[] getGroups(Player player) {
    return permission.getPlayerGroups(player);
  }
}
