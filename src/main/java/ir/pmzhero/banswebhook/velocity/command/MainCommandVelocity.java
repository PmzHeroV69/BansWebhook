package ir.pmzhero.banswebhook.velocity.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import ir.pmzhero.banswebhook.common.BansWebhook;
import ir.pmzhero.banswebhook.common.data.ConfigLoader;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

@RequiredArgsConstructor
public class MainCommandVelocity implements SimpleCommand {

    private final BansWebhook core;

    @Override
    public void execute(Invocation invocation) {

        CommandSource source = invocation.source();
        String[] args = invocation.arguments();

        if (!source.hasPermission("bwh.admin")) {
            source.sendMessage(Component.text("§cYou are not allowed to use this command!"));
            return;
        }

        if (args.length == 0) {
            source.sendMessage(Component.text("§c/bwh <reload/debug>"));
            return;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                core.getYmlConfig().reloadConfig();
                core.setConfig(ConfigLoader.load(core.getYmlConfig(), core.getWebhookManager()));

                if (core.isWebhooksLoaded()) {
                    source.sendMessage(LegacyComponentSerializer.builder().character('&').extractUrls().build().deserialize(core.getConfig().getReloadMessage()));
                } else {
                    source.sendMessage(Component.text("§cWebhooks can't be loaded!"));
                }
                break;
            case "debug":
                if (args.length != 2) {
                    source.sendMessage(Component.text("§c/bwh debug <ban/kick/warn/mute>"));
                    return;
                }

                String type = args[1];

                if (!(type.equals("ban") || type.equals("kick") || type.equals("warn") || type.equals("mute"))) {
                    source.sendMessage(Component.text("§c/bwh debug <ban/kick/warn/mute>"));
                    return;
                }

                core.getWebhookManager().sendPunishmentWebhook("Debug", "Debug", "Debug", "Debug", type);

                source.sendMessage(Component.text("§aSENT DEBUG"));
                break;
            default:
                source.sendMessage(Component.text("§c/bwh <reload/debug>"));
                break;
        }
    }
}
