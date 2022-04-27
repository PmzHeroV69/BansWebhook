package ir.pmzhero.banswebhook.shared.data;

import net.md_5.bungee.api.ChatColor;

public class ConfigLoader {

    public static Config load(YmlConfig config) {

        return new Config(
                ChatColor.translateAlternateColorCodes('&', config.getString(ConfigPath.RELOAD_MESSAGE)),
                config.getBoolean(ConfigPath.LITEBANS_DO_NOT_SEND_PUNISHMENT),
                config.getBoolean(ConfigPath.INLINE_WEBHOOKS),

                config.getBoolean(ConfigPath.BAN_ENABLED),
                config.getString(ConfigPath.BAN_WEBHOOK_URL),
                config.getString(ConfigPath.BAN_WEBHOOK_TITLE),
                config.getString(ConfigPath.BAN_WEBHOOK_THUMBNAIL),
                Integer.parseInt(config.getString(ConfigPath.BAN_WEBHOOK_COLOR), 16),
                config.getSectionKeys(ConfigPath.BAN_FIELDS_SECTION),

                config.getBoolean(ConfigPath.MUTE_ENABLED),
                config.getString(ConfigPath.MUTE_WEBHOOK_URL),
                config.getString(ConfigPath.MUTE_WEBHOOK_TITLE),
                config.getString(ConfigPath.MUTE_WEBHOOK_THUMBNAIL),
                Integer.parseInt(config.getString(ConfigPath.MUTE_WEBHOOK_COLOR), 16),
                config.getSectionKeys(ConfigPath.MUTE_FIELDS_SECTION),

                config.getBoolean(ConfigPath.WARN_ENABLED),
                config.getString(ConfigPath.WARN_WEBHOOK_URL),
                config.getString(ConfigPath.WARN_WEBHOOK_TITLE),
                config.getString(ConfigPath.WARN_WEBHOOK_THUMBNAIL),
                Integer.parseInt(config.getString(ConfigPath.WARN_WEBHOOK_COLOR), 16),
                config.getSectionKeys(ConfigPath.WARN_FIELDS_SECTION),

                config.getBoolean(ConfigPath.KICK_ENABLED),
                config.getString(ConfigPath.KICK_WEBHOOK_URL),
                config.getString(ConfigPath.KICK_WEBHOOK_TITLE),
                config.getString(ConfigPath.KICK_WEBHOOK_THUMBNAIL),
                Integer.parseInt(config.getString(ConfigPath.KICK_WEBHOOK_COLOR), 16),
                config.getSectionKeys(ConfigPath.KICK_FIELDS_SECTION)
        );

    }

}
