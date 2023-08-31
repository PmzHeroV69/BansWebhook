package ir.pmzhero.banswebhook.bungeecord.command;

import ir.pmzhero.banswebhook.common.command.MessageReceiver;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

@RequiredArgsConstructor
public final class BungeeReceiver implements MessageReceiver {

    private final CommandSender recipient;

    @Override
    public void sendMessage(String text) {
        recipient.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', text)));
    }
}
