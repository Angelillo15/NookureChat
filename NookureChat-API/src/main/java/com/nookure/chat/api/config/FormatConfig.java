package com.nookure.chat.api.config;

import com.nookure.chat.api.config.partials.format.HoverFormatConfig;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.Map;

@ConfigSerializable
public class FormatConfig {
  @Setting
  private String defaultPrefix = "";
  @Setting
  private String defaultSuffix = "";
  @Setting
  private String defaultFormat = "{prefix} <reset>{displayname} <gray><b>»</b></gray> {message}";
  @Setting
  private String defaultJoinMessage = "<green><b>+ <reset>{displayname}";
  @Setting
  private String defaultQuitMessage = "<red><b>- <reset>{displayname}";
  @Setting
  private String defaultTitleJoinMessage = "<red><b>Welcome to <white>Nookure<red>!";
  @Setting
  private String defaultSubtitleJoinMessage = "<gray>Enjoy your stay!";
  @Setting
  private String firstJoinMessage = "{player} <gray>has joined for the first time!";
  @Setting
  private boolean enableJoinQuitMessages = true;
  @Setting
  private boolean enableJoinTitles = true;
  @Setting
  public final HoverFormatConfig hoverFormat = new HoverFormatConfig();

  public String getDefaultJoinMessage() {
    return defaultJoinMessage;
  }

  public String getDefaultQuitMessage() {
    return defaultQuitMessage;
  }

  public String getDefaultPrefix() {
    return defaultPrefix;
  }

  public String getDefaultFormat() {
    return defaultFormat;
  }

  public String getDefaultSuffix() {
    return defaultSuffix;
  }

  public boolean isEnableJoinQuitMessages() {
    return enableJoinQuitMessages;
  }

  public boolean isEnableJoinTitles() {
    return enableJoinTitles;
  }

  public String getDefaultTitleJoinMessage() {
    return defaultTitleJoinMessage;
  }

  public String getDefaultSubtitleJoinMessage() {
    return defaultSubtitleJoinMessage;
  }
  public String getFirstJoinMessage() {
    return firstJoinMessage;
  }

  @Setting
  private Map<String, Group> groups = Map.of("helper", new Group(), "mod", new Group(), "admin", new Group(), "owner", new Group());

  public Map<String, Group> getGroups() {
    return groups;
  }

  @ConfigSerializable
  public static final class Group {
    public Group() {
    }

    @Setting
    private String prefix = "";

    @Setting
    private String suffix = "";
    @Setting
    private String format = "{default}";
    @Setting
    private String joinMessage = "{default}";
    @Setting
    private String quitMessage = "{default}";
    @Setting
    private String titleJoinMessage = "{default}";
    @Setting
    private String subtitleJoinMessage = "{default}";

    public String getSuffix() {
      return suffix;
    }

    public String getPrefix() {
      return prefix;
    }

    public String getFormat() {
      return format;
    }

    public String getJoinMessage() {
      return joinMessage;
    }

    public String getQuitMessage() {
      return quitMessage;
    }

    public String getTitleJoinMessage() {
      return titleJoinMessage;
    }

    public String getSubtitleJoinMessage() {
      return subtitleJoinMessage;
    }
  }
}
