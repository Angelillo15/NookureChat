package com.nookure.chat.api.config;

import com.nookure.chat.api.config.partials.FloodConfig;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public class Config {
  @Setting
  private boolean debug = false;
  @Setting
  private String prefix = "<b><red>Chat</red></b> <b><gray>»</gray></b>";
  @Setting
  public FloodConfig flood = new FloodConfig();

  public String getPrefix() {
    return prefix;
  }

  public boolean isDebug() {
    return debug;
  }

}
