package ir.pmzhero.banswebhook.bungeecord.command;

import ir.pmzhero.banswebhook.common.BansWebhook;
import ir.pmzhero.banswebhook.common.command.CommandHandler;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public final class MainCommandBungee extends Command {

    private final BansWebhook core;

    public MainCommandBungee(BansWebhook core) {
        super("bwh", "bwh.admin", "banswebhook");
        this.core = core;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        CommandHandler.handleCommand(new BungeeReceiver(sender), args, core);
    }
}
