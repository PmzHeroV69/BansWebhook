package ir.pmzhero.banswebhook.common.command;

import ir.pmzhero.banswebhook.common.BansWebhook;
import ir.pmzhero.banswebhook.common.data.ConfigLoader;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;

public final class CommandHandler {

    private static final List<String> allowed = Arrays.asList("ban", "mute", "warn", "kick", "unban", "unmute", "unwarn");

    public static void handleCommand(MessageReceiver recipient, String[] args, BansWebhook core) {
        if (args.length == 0) {
            recipient.sendMessage(ChatColor.RED + "/bwh <reload/debug>");
            return;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                core.getYmlConfig().reloadConfig();
                core.setConfig(ConfigLoader.load(core.getYmlConfig(), core.getWebhookManager()));
                if (core.isWebhooksLoaded()) {
                    recipient.sendMessage(core.getConfig().getReloadMessage());
                } else {
                    recipient.sendMessage("&cWebhooks can't be loaded!");
                }
                break;
            case "debug":
                if (args.length != 2) {
                    recipient.sendMessage("&c/bwh debug <ban/mute/warn/kick/unban/unmute/unwarn>");
                    return;
                }

                String type = args[1];
                if (!allowed.contains(type)) {
                    recipient.sendMessage("&c/bwh debug <ban/mute/warn/kick/unban/unmute/unwarn>");
                }

                core.getWebhookManager().sendPunishmentWebhook("Debug", 0, "Debug", "Debug", "Debug", type);
                recipient.sendMessage("&aSent Debug Message");
                break;
            default:
                recipient.sendMessage("&c/bwh <reload/debug>");
                break;
        }
    }
}
