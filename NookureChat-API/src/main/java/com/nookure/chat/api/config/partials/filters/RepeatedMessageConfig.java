package com.nookure.chat.api.config.partials.filters;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public class RepeatedMessageConfig extends FilterConfig {
  @Setting
  private boolean enabled = true;
  @Setting
  private String denyMessage = "&cYou can't send the same message twice! Please wait {time} seconds.";
  @Setting
  private int timeRestriction = 60;

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public String getDenyMessage() {
    return denyMessage;
  }

  public int getTimeRestriction() {
    return timeRestriction;
  }
}
