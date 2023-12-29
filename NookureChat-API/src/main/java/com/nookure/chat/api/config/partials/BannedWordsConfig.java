package com.nookure.chat.api.config.partials;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public class BannedWordsConfig extends FilterConfig {
  @Setting
  private boolean enabled = true;
  @Setting
  private String denyMessage = "<red>You are not allowed to say that!";
  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public String getDenyMessage() {
    return denyMessage;
  }
}
