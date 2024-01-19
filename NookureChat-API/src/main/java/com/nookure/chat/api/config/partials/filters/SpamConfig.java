package com.nookure.chat.api.config.partials.filters;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.List;

@ConfigSerializable
public class SpamConfig extends FilterConfig {
  @Setting
  private boolean enabled = true;
  @Setting
  private String denyMessage = "{prefix} <red>The domains and IPs are forbidden!</red>";

  @Setting
  private List<String> whitelistedDomains = List.of("nookure.com", "nookure.host", "angelillo15.es");

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public String getDenyMessage() {
    return denyMessage;
  }

  public List<String> getWhitelistedDomains() {
    return whitelistedDomains;
  }
}
