package com.nookure.chat.api.config;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

import java.util.HashMap;
import java.util.Map;

@ConfigSerializable
public class FormatConfig {
  @Setting
  private String defaultPrefix = "";

  public String getDefaultPrefix() {
    return defaultPrefix;
  }

  @Setting
  private String defaultSuffix = "";

  public String getDefaultSuffix() {
    return defaultSuffix;
  }

  @Setting
  private String defaultFormat = "{prefix} <reset>{displayname} <gray><b>»</b></gray> {message}";

  public String getDefaultFormat() {
    return defaultFormat;
  }

  @Setting
  private Map<String, Group> groups = Map.of(
      "helper", new Group(),
      "mod", new Group(),
      "admin", new Group(),
      "owner", new Group()
  );

  public Map<String, Group> getGroups() {
    return groups;
  }

  @ConfigSerializable
  public static final class Group {
    public Group() {
    }

    @Setting
    private String prefix = "";

    public String getPrefix() {
      return prefix;
    }

    @Setting
    private String suffix = "";

    public String getSuffix() {
      return suffix;
    }

    @Setting
    private String format = "{default}";

    public String getFormat() {
      return format;
    }
  }
}
