package ir.pmzhero.banswebhook.bungeecord.command;

import ir.pmzhero.banswebhook.shared.BansWebhook;
import ir.pmzhero.banswebhook.shared.data.ConfigLoader;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class MainCommandBungee extends Command {

    private final BansWebhook core;

    public MainCommandBungee(BansWebhook core) {
        super("bwh", "bwh.admin", "banswebhook");
        this.core = core;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (args.length != 1) {
            sender.sendMessage(TextComponent.fromLegacyText(ChatColor.RED + "/bwh reload"));
            return;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            core.getYmlConfig().reloadConfig();
            core.setConfig(ConfigLoader.load(core.getYmlConfig()));
            sender.sendMessage(TextComponent.fromLegacyText(core.getConfig().getReloadMessage()));
        } else {
            sender.sendMessage(TextComponent.fromLegacyText(ChatColor.RED + "/bwh reload"));
        }

    }
}
