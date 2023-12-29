package com.nookure.chat.api.config.partials.filters;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public class MessageSpamConfig extends FilterConfig {
  @Setting
  private boolean enabled = true;
  @Setting
  private int cooldown = 3;
  @Setting
  private String denyMessage = "<red>You are sending messages too fast! Please wait {time} seconds before sending another message.";
  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public String getDenyMessage() {
    return denyMessage;
  }

  public int getCooldown() {
    return cooldown;
  }
}
