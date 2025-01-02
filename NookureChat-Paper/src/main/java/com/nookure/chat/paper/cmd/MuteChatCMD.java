package com.nookure.chat.paper.cmd;

import com.google.inject.Inject;
import com.nookure.chat.api.TextUtils;
import com.nookure.chat.api.annotation.MuteChatActive;
import com.nookure.chat.api.config.Config;
import com.nookure.chat.api.config.ConfigurationContainer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

public class MuteChatCMD extends Command {
  private final AtomicBoolean muteChatActive;
  private final ConfigurationContainer<Config> config;

  @Inject
  public MuteChatCMD(
      @NotNull @MuteChatActive final AtomicBoolean muteChatActive,
      @NotNull final ConfigurationContainer<Config> config
  ) {
    super("mutechat");
    setPermission("nookurechat.mutechat");
    setDescription("Mute the chat.");

    this.muteChatActive = muteChatActive;
    this.config = config;
  }


  @Override
  public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
    if (muteChatActive.get()) {
      muteChatActive.set(false);
      TextUtils.sendMessage(sender, config.get().messages.youHaveDisabledMuteChat);
      TextUtils.broadcastMessage(config.get().messages.theChatHasBeenUnmuted);
    } else {
      muteChatActive.set(true);
      TextUtils.sendMessage(sender, config.get().messages.youHaveEnabledMuteChat);
      TextUtils.broadcastMessage(config.get().messages.theChatHasBeenMuted);
    }

    return true;
  }
}
