package ir.pmzhero.banswebhook.spigot.command;

import ir.pmzhero.banswebhook.common.command.MessageReceiver;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@RequiredArgsConstructor
public final class SpigotReceiver implements MessageReceiver {

    private final CommandSender sender;

    @Override
    public void sendMessage(String text) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    }
}
