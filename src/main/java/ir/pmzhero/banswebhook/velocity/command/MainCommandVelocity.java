package ir.pmzhero.banswebhook.velocity.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import ir.pmzhero.banswebhook.common.BansWebhook;
import ir.pmzhero.banswebhook.common.command.CommandHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MainCommandVelocity implements SimpleCommand {

    private final BansWebhook core;

    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();

        CommandHandler.handleCommand(new VelocityReceiver(source), args, core);
    }
}
