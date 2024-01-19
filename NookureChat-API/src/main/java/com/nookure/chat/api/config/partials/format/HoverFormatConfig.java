package com.nookure.chat.api.config.partials.format;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.List;

@ConfigSerializable
public class HoverFormatConfig {
  @Setting
  private boolean enabled = true;
  @Setting
  private List<String> lines = List.of(
    "<gray> Name: <reset>%player_name%"
  );

  public boolean isEnabled() {
    return enabled;
  }

  public List<String> getLines() {
    return lines;
  }
}
