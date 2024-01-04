package com.nookure.chat.api.config.partials.config;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public class ClearChatPartial {
  @Setting
  private boolean enabled = true;
  @Setting
  private String successful = "<green>Chat has been cleared successfully!";

  public boolean isEnabled() {
    return enabled;
  }

  public String getSuccessful() {
    return successful;
  }
}
