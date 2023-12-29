package com.nookure.chat.api.config;

import com.nookure.chat.api.config.partials.FloodConfig;
import com.nookure.chat.api.config.partials.SpamConfig;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public class Config {
  @Setting
  private boolean debug = false;
  @Setting
  private String prefix = "<b><red>Chat</red></b> <b><gray>»</gray></b>";
  public Filters filters = new Filters();

  @ConfigSerializable
  public static final class Filters {
    @Setting
    public FloodConfig flood = new FloodConfig();
    @Setting
    public SpamConfig spam = new SpamConfig();
  }

  public String getPrefix() {
    return prefix;
  }

  public boolean isDebug() {
    return debug;
  }

}
