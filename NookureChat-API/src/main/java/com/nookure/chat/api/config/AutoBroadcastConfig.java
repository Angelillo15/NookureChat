package com.nookure.chat.api.config;

import com.nookure.chat.api.config.partials.broadcast.BroadcastGroupPartial;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.Map;

@ConfigSerializable
public class AutoBroadcastConfig {
  @Setting
  private boolean enabled = true;

  @Setting
  private Map<String, BroadcastGroupPartial> broadcasts = Map.of(
      "default", new BroadcastGroupPartial()
  );

  public boolean isEnabled() {
    return enabled;
  }

  public Map<String, BroadcastGroupPartial> getBroadcasts() {
    return broadcasts;
  }
}
