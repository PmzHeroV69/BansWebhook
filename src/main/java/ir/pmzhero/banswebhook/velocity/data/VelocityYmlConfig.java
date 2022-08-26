package ir.pmzhero.banswebhook.velocity.data;

import ir.pmzhero.banswebhook.common.data.Pair;
import ir.pmzhero.banswebhook.common.data.YmlConfig;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class VelocityYmlConfig implements YmlConfig {

    private final VelocityConfigFile configFile;

    @Override
    public String getString(String path) {
        return (String) configFile.get(path);
    }

    @Override
    public boolean getBoolean(String path) {
        return (boolean) configFile.get(path);
    }

    @Override
    public List<Pair<String, String>> getSectionKeys(String path) {

        List<Pair<String, String>> pairs = new ArrayList<>();

        for (Map.Entry<String, Object> entry : configFile.getSection(path).entrySet()) {
            pairs.add(new Pair<>(entry.getKey(), (String) entry.getValue()));
        }
        return pairs;
    }

    @Override
    public void reloadConfig() {
        configFile.load();
    }
}
