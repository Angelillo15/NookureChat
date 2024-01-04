package com.nookure.chat.api.config.partials.broadcast;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.List;

@ConfigSerializable
public class BroadcastGroupPartial {
  @Setting
  private boolean enabled = true;
  @Setting
  private int interval = 60;
  @Setting
  private String header = "<dark_gray><st>------------------------";
  @Setting
  private String footer = "<dark_gray><st>------------------------";
  @Setting
  private String prefix = "<b><gold>Broadcast</gold> <dark_gray>»</dark_gray></b>";
  @Setting
  private SoundPartial sound = new SoundPartial();
  @Setting
  private List<String> messages = List.of(
      "{prefix} <gray>Join our discord server: <hover:show_text:'<b><gold>Click to join</gold></b>'><click:open_url:'https://discord.gg/'>https://discord.gg/</click></hover>",
      """
          {center}<gray>¿Do you find any hacker?</gray>

          {center}<gray>Report it on our <hover:show_text:'<light_purple>Open Discord'><click:open_url:'discord.gg'><blue>discord</blue></click></hover> server
                """
  );

  public boolean isEnabled() {
    return enabled;
  }

  public int getInterval() {
    return interval;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  public String getPrefix() {
    return prefix;
  }

  public SoundPartial getSound() {
    return sound;
  }

  public List<String> getMessages() {
    return messages;
  }
}
