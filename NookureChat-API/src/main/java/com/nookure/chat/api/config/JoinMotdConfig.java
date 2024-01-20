package com.nookure.chat.api.config;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public class JoinMotdConfig {
  @Setting
  private boolean enabled = true;

  @Setting
  private String motd = """
      <gray> Welcome to <reset>Nookure<gray>!
      <gray> Enjoy your stay!
      """;

  public boolean isEnabled() {
    return enabled;
  }

  public String getMotd() {
    return motd;
  }
}
