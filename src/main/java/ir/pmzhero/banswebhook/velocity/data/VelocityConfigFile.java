package ir.pmzhero.banswebhook.velocity.data;

import lombok.Getter;
import lombok.SneakyThrows;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class VelocityConfigFile {

    @Getter
    private Map<String, Object> config;

    private final Path dataDirectory;
    private final String fileName;

    public VelocityConfigFile(Path dataDirectory, String fileName) {
        this.dataDirectory = dataDirectory;
        this.fileName = fileName;
        load();
    }

    @SneakyThrows
    public void load() {
        File file = saveResource(dataDirectory, fileName);
        assert file != null;

        this.config = new Yaml().load(new FileInputStream(file));
    }

    @SneakyThrows
    private File saveResource(Path dataDirectory, String fileName) {

        File configFile = new File(dataDirectory.toFile(), fileName);

        if (!configFile.exists()) {

            if (!configFile.createNewFile()) {
                return null;
            }

            URL url = getClass().getClassLoader().getResource(fileName);
            assert url != null;

            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);

            InputStream in = connection.getInputStream();
            OutputStream out = new FileOutputStream(configFile);

            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            out.close();
            in.close();
        }
        return configFile;
    }

    public Object get(String path) {

        if (path.contains(".")) {

            String[] split = path.split("\\.");

            Object object = directGet(split[0]);
            if (object instanceof LinkedHashMap) {
                LinkedHashMap<String, Object> section = (LinkedHashMap<String, Object>) object;
                return section.get(split[1]);
            } else {
                return null;
            }
        } else {
            return directGet(path);
        }
    }

    public Map<String, Object> getSection(String path) {

        Object object = get(path);
        if (object instanceof LinkedHashMap) {
            return (LinkedHashMap<String, Object>) object;
        } else {
            return null;
        }
    }

    private Object directGet(String path) {
        return config.get(path);
    }
}
