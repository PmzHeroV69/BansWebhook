package ir.pmzhero.banswebhook.shared.data;

import java.util.List;

public interface YmlConfig {

    String getString(String path);

    int getInt(String path);

    boolean getBoolean(String path);

    List<Pair<String, String>> getSectionKeys(String path);

    void reloadConfig();

}
