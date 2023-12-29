package com.nookure.chat.api.config.partials.filters;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public class SpamConfig extends FilterConfig {
  @Setting
  private boolean enabled = true;
  @Setting
  private String denyMessage = "{prefix} <red>The domains and IPs are forbidden!</red>";

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public String getDenyMessage() {
    return denyMessage;
  }
}
