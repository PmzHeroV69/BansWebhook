package ir.pmzhero.banswebhook.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import ir.pmzhero.banswebhook.common.BansWebhook;
import ir.pmzhero.banswebhook.common.litebans.LitebansListener;
import ir.pmzhero.banswebhook.velocity.command.MainCommandVelocity;
import ir.pmzhero.banswebhook.velocity.data.VelocityConfigFile;
import ir.pmzhero.banswebhook.velocity.data.VelocityYmlConfig;
import org.slf4j.Logger;

import java.io.File;
import java.nio.file.Path;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class BansWebhookVelocity {

    private final BansWebhook core;
    private final ProxyServer server;

    @Inject
    public BansWebhookVelocity(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {

        this.server = server;

        File file = dataDirectory.toFile();
        if (!file.exists()) {
            file.mkdir();
        }

        this.core = new BansWebhook(new VelocityYmlConfig(new VelocityConfigFile(dataDirectory, "config.yml")));

        if (core.isWebhooksLoaded()) {
            logger.info("Successfully Enabled BansWebhook $(version) by PmzHero");
        } else {
            logger.error("Webhooks can't be loaded!");
        }
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        LitebansListener.register(core);

        CommandManager manager = server.getCommandManager();
        CommandMeta meta = manager.metaBuilder("bwh")
                .aliases("banswebhook")
                .build();
        manager.register(meta, new MainCommandVelocity(core));
    }
}
