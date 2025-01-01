package com.nookure.chat.api.config.partials.config;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Setting;

@ConfigSerializable
public class Messages {
  @Setting
  public String youHaveEnabledMuteChat = "<green>You have enabled the chat mute!";

  @Setting
  public String youHaveDisabledMuteChat = "<green>You have disabled the chat mute!";

  @Setting
  public String youCantTalk = "<red>You can't talk while the chat is muted!";

  @Setting
  public String theChatHasBeenMuted = "<red>The chat has been muted!";

  @Setting
  public String theChatHasBeenUnmuted = "<green>The chat has been unmuted!";
}
