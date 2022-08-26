package ir.pmzhero.banswebhook.bungeecord.data;

import ir.pmzhero.banswebhook.common.data.Pair;
import ir.pmzhero.banswebhook.common.data.YmlConfig;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BungeeYmlConfig implements YmlConfig {

    private final BungeeConfigFile file;

    @Override
    public String getString(String path) {
        return file.getConfig().getString(path);
    }

    @Override
    public boolean getBoolean(String path) {
        return file.getConfig().getBoolean(path);
    }

    @Override
    public List<Pair<String, String>> getSectionKeys(String path) {
        Configuration section = file.getConfig().getSection(path);
        List<Pair<String, String>> pairs = new ArrayList<>();

        for (String key : section.getKeys()) {
            pairs.add(new Pair<>(key, section.getString(key)));
        }
        return pairs;
    }

    @Override
    public void reloadConfig() {
        file.reloadConfig();
    }
}
