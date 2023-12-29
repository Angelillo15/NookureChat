package com.nookure.chat.paper.cmd;

import com.google.inject.Inject;
import com.nookure.chat.api.NookureChat;
import com.nookure.chat.api.cmd.SubCommand;
import com.nookure.chat.api.cmd.SubCommandData;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import static com.nookure.chat.paper.utils.MessageUtils.sendMessage;

@SubCommandData(
    name = "reload",
    description = "Subcommand to reload the plugin",
    permission = "nookurechat.admin"
)
public class ReloadArg extends SubCommand {
  @Inject
  private NookureChat plugin;
  @Override
  public void execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] strings) {
    plugin.reload();
    sendMessage(sender, "<green>NookureChat has been successfully reloaded!");
  }
}
