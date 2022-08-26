package ir.pmzhero.banswebhook.bungeecord.command;

import ir.pmzhero.banswebhook.common.BansWebhook;
import ir.pmzhero.banswebhook.common.data.ConfigLoader;
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

        if (args.length == 0) {
            sender.sendMessage(TextComponent.fromLegacyText(ChatColor.RED + "/bwh <reload/debug>"));
            return;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                core.getYmlConfig().reloadConfig();
                core.setConfig(ConfigLoader.load(core.getYmlConfig(), core.getWebhookManager()));
                if (core.isWebhooksLoaded()) {
                    sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', core.getConfig().getReloadMessage())));
                } else {
                    sender.sendMessage(TextComponent.fromLegacyText(ChatColor.RED + "Webhooks can't be loaded!"));
                }
                break;
            case "debug":
                if (args.length != 2) {
                    sender.sendMessage(TextComponent.fromLegacyText(ChatColor.RED + "/bwh debug <ban/kick/warn/mute>"));
                    return;
                }

                String type = args[1];

                if (!(type.equals("ban") || type.equals("kick") || type.equals("warn") || type.equals("mute"))) {
                    sender.sendMessage(TextComponent.fromLegacyText(ChatColor.RED + "/bwh debug <ban/kick/warn/mute>"));
                    return;
                }

                core.getWebhookManager().sendPunishmentWebhook("Debug", "Debug", "Debug", "Debug", type);

                sender.sendMessage(TextComponent.fromLegacyText(ChatColor.GREEN + "SENT DEBUG"));
                break;
            default:
                sender.sendMessage(TextComponent.fromLegacyText(ChatColor.RED + "/bwh <reload/debug>"));
                break;

        }
    }
}
