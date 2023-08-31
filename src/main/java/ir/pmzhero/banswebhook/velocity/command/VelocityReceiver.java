package ir.pmzhero.banswebhook.velocity.command;

import com.velocitypowered.api.command.CommandSource;
import ir.pmzhero.banswebhook.common.command.MessageReceiver;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

@RequiredArgsConstructor
public class VelocityReceiver implements MessageReceiver {

    private final CommandSource source;

    @Override
    public void sendMessage(String text) {
        source.sendMessage(LegacyComponentSerializer.builder().character('&').extractUrls().build().deserialize(text));
    }
}
