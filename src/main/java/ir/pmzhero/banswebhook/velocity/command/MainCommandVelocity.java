package ir.pmzhero.banswebhook.velocity.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import ir.pmzhero.banswebhook.shared.BansWebhook;
import ir.pmzhero.banswebhook.shared.data.ConfigLoader;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

@RequiredArgsConstructor
public class MainCommandVelocity implements SimpleCommand {

    private final BansWebhook core;

    @Override
    public void execute(Invocation invocation) {

        CommandSource source = invocation.source();
        String[] args = invocation.arguments();

        if (args.length == 0) {
            source.sendMessage(Component.text("/bwh <reload/debug>").color(NamedTextColor.RED));
            return;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                core.getYmlConfig().reloadConfig();
                core.setConfig(ConfigLoader.load(core.getYmlConfig(), core.getWebhookManager()));

                if (core.isWebhooksLoaded()) {
                    source.sendMessage(LegacyComponentSerializer.builder().character('&').extractUrls().build().deserialize(core.getConfig().getReloadMessage()));
                } else {
                    source.sendMessage(Component.text("Webhooks can't be loaded!").color(NamedTextColor.RED));
                }
                break;
            case "debug":
                if (args.length != 2) {
                    source.sendMessage(Component.text("/bwh debug <ban/kick/warn/mute>").color(NamedTextColor.RED));
                    return;
                }

                String type = args[1];

                if (!(type.equals("ban") || type.equals("kick") || type.equals("warn") || type.equals("mute"))) {
                    source.sendMessage(Component.text("/bwh debug <ban/kick/warn/mute>").color(NamedTextColor.RED));
                    return;
                }

                core.getWebhookManager().sendPunishmentWebhook("Debug", "Debug", "Debug", "Debug", type);

                source.sendMessage(Component.text("SENT DEBUG").color(NamedTextColor.GREEN));
                break;
            default:
                source.sendMessage(Component.text("/bwh <reload/debug>").color(NamedTextColor.RED));
                break;
        }
    }

    @Override
    public boolean hasPermission(Invocation invocation) {
        return invocation.source().hasPermission("bwh.admin");
    }
}
