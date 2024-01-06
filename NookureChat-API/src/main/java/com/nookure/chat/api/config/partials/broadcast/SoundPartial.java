package com.nookure.chat.api.config.partials.broadcast;

import com.nookure.chat.api.NookureChat;
import org.bukkit.Sound;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public class SoundPartial {
  private static final String SOUND;
  static {
    if (NookureChat.VERSION < 13) {
      SOUND = "LEVEL_UP";
    } else {
      SOUND = "ENTITY_PLAYER_LEVELUP";
    }
  }

  @Setting
  private boolean enabled = true;
  @Setting
  private Sound sound = Sound.valueOf(SOUND);
  @Setting
  private float volume = 1.0F;
  @Setting
  private float pitch = 1.0F;

  public boolean isEnabled() {
    return enabled;
  }

  public Sound getSound() {
    return sound;
  }

  public float getVolume() {
    return volume;
  }

  public float getPitch() {
    return pitch;
  }
}
