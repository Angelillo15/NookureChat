package com.nookure.chat.api.config;

import com.nookure.chat.api.config.partials.filters.BannedWordsConfig;
import com.nookure.chat.api.config.partials.filters.FloodConfig;
import com.nookure.chat.api.config.partials.filters.MessageSpamConfig;
import com.nookure.chat.api.config.partials.filters.SpamConfig;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public class Config {
  @Setting
  private boolean debug = false;
  @Setting
  private String prefix = "<b><red>Chat</red></b> <b><gray>»</gray></b>";
  @Setting
  public Filters filters = new Filters();

  @ConfigSerializable
  public static final class Filters {
    @Setting
    public FloodConfig flood = new FloodConfig();
    @Setting
    public SpamConfig spam = new SpamConfig();
    @Setting
    public MessageSpamConfig messageSpam = new MessageSpamConfig();
    @Setting
    public BannedWordsConfig bannedWords = new BannedWordsConfig();
  }

  public String getPrefix() {
    return prefix;
  }

  public boolean isDebug() {
    return debug;
  }
}
