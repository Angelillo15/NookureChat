package com.nookure.chat.api.adapters;

import org.bukkit.entity.Player;

public abstract class PermissionAdapter {
  abstract public String getHighestGroup(Player player);
}
