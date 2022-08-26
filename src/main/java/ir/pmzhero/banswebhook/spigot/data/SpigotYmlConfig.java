package ir.pmzhero.banswebhook.spigot.data;

import ir.pmzhero.banswebhook.common.data.Pair;
import ir.pmzhero.banswebhook.common.data.YmlConfig;
import ir.pmzhero.banswebhook.spigot.BansWebhookSpigot;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;

import java.util.*;

@RequiredArgsConstructor
public class SpigotYmlConfig implements YmlConfig {

    private final BansWebhookSpigot plugin;

    @Override
    public String getString(String path) {
        return plugin.getConfig().getString(path);
    }

    @Override
    public boolean getBoolean(String path) {
        return plugin.getConfig().getBoolean(path);
    }

    @Override
    public List<Pair<String, String>> getSectionKeys(String path) {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection(path);
        List<Pair<String, String>> pairs = new ArrayList<>();

        for (String key : section.getKeys(false)) {
            pairs.add(new Pair<>(key, section.getString(key)));
        }
        return pairs;
    }

    @Override
    public void reloadConfig() {
        plugin.reloadConfig();
    }
}
