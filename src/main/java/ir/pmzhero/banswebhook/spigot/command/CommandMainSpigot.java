package ir.pmzhero.banswebhook.spigot.command;

import ir.pmzhero.banswebhook.common.BansWebhook;
import ir.pmzhero.banswebhook.common.data.ConfigLoader;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@RequiredArgsConstructor
public class CommandMainSpigot implements CommandExecutor {

    private final BansWebhook core;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "/bwh <reload/debug>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                core.getYmlConfig().reloadConfig();
                core.setConfig(ConfigLoader.load(core.getYmlConfig(), core.getWebhookManager()));
                if (core.isWebhooksLoaded()) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', core.getConfig().getReloadMessage()));
                } else {
                    sender.sendMessage(ChatColor.RED + "Webhooks can't be loaded!");
                }
                break;
            case "debug":
                if (args.length != 2) {
                    sender.sendMessage(ChatColor.RED + "/bwh debug <ban/kick/warn/mute>");
                    return true;
                }

                String type = args[1];

                if (!(type.equals("ban") || type.equals("kick") || type.equals("warn") || type.equals("mute"))) {
                    sender.sendMessage(ChatColor.RED + "/bwh debug <ban/kick/warn/mute>");
                    return true;
                }

                core.getWebhookManager().sendPunishmentWebhook("Debug", "Debug", "Debug", "Debug", type);

                sender.sendMessage(ChatColor.GREEN + "SENT DEBUG");
                break;
            default:
                sender.sendMessage(ChatColor.RED + "/bwh <reload/debug>");
                break;
        }

        return true;
    }
}
