package ir.pmzhero.banswebhook.bungeecord.data;

import ir.pmzhero.banswebhook.bungeecord.BansWebhookBungee;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class BungeeConfigFile {

    private final BansWebhookBungee plugin;
    private final String fileName;
    private Configuration configuration;

    public BungeeConfigFile(BansWebhookBungee plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;

        try {
            saveDefaultConfig();
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), fileName));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Configuration getConfig() {
        return configuration;
    }

    public void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(plugin.getDataFolder(), fileName));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void reloadConfig() {
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(plugin.getDataFolder(), fileName));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public void saveDefaultConfig() {
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();

        File file = new File(plugin.getDataFolder(), fileName);


        if (!file.exists()) {
            try (InputStream in = plugin.getResourceAsStream(fileName)) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
