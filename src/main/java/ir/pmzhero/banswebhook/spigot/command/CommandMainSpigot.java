package ir.pmzhero.banswebhook.spigot.command;

import ir.pmzhero.banswebhook.shared.BansWebhook;
import ir.pmzhero.banswebhook.shared.data.ConfigLoader;
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

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "/bwh reload");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            core.getYmlConfig().reloadConfig();
            core.setConfig(ConfigLoader.load(core.getYmlConfig()));
            sender.sendMessage(core.getConfig().getReloadMessage());
        } else {
            sender.sendMessage(ChatColor.RED + "/bwh reload");
        }

        return true;
    }
}
