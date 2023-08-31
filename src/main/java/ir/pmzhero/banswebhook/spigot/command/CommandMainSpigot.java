package ir.pmzhero.banswebhook.spigot.command;

import ir.pmzhero.banswebhook.common.BansWebhook;
import ir.pmzhero.banswebhook.common.command.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public final class CommandMainSpigot implements CommandExecutor {

    private final BansWebhook core;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        CommandHandler.handleCommand(new SpigotReceiver(sender), args, core);
        return true;
    }
}
