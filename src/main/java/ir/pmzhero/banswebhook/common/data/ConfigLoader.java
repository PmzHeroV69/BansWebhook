package ir.pmzhero.banswebhook.common.data;

import ir.pmzhero.banswebhook.common.webhook.WebhookManager;

public class ConfigLoader {

    public static Config load(YmlConfig ymlConfig, WebhookManager manager) {

        Config config = new Config(
                ymlConfig.getString(ConfigPath.RELOAD_MESSAGE),
                ymlConfig.getBoolean(ConfigPath.LITEBANS_DO_NOT_SEND_PUNISHMENT),
                ymlConfig.getBoolean(ConfigPath.INLINE_WEBHOOKS),

                ymlConfig.getBoolean(ConfigPath.BAN_ENABLED),
                ymlConfig.getString(ConfigPath.BAN_WEBHOOK_URL),
                ymlConfig.getString(ConfigPath.BAN_WEBHOOK_TITLE),
                ymlConfig.getString(ConfigPath.BAN_WEBHOOK_THUMBNAIL),
                Integer.parseInt(ymlConfig.getString(ConfigPath.BAN_WEBHOOK_COLOR), 16),
                ymlConfig.getSectionKeys(ConfigPath.BAN_FIELDS_SECTION),

                ymlConfig.getBoolean(ConfigPath.MUTE_ENABLED),
                ymlConfig.getString(ConfigPath.MUTE_WEBHOOK_URL),
                ymlConfig.getString(ConfigPath.MUTE_WEBHOOK_TITLE),
                ymlConfig.getString(ConfigPath.MUTE_WEBHOOK_THUMBNAIL),
                Integer.parseInt(ymlConfig.getString(ConfigPath.MUTE_WEBHOOK_COLOR), 16),
                ymlConfig.getSectionKeys(ConfigPath.MUTE_FIELDS_SECTION),

                ymlConfig.getBoolean(ConfigPath.WARN_ENABLED),
                ymlConfig.getString(ConfigPath.WARN_WEBHOOK_URL),
                ymlConfig.getString(ConfigPath.WARN_WEBHOOK_TITLE),
                ymlConfig.getString(ConfigPath.WARN_WEBHOOK_THUMBNAIL),
                Integer.parseInt(ymlConfig.getString(ConfigPath.WARN_WEBHOOK_COLOR), 16),
                ymlConfig.getSectionKeys(ConfigPath.WARN_FIELDS_SECTION),

                ymlConfig.getBoolean(ConfigPath.KICK_ENABLED),
                ymlConfig.getString(ConfigPath.KICK_WEBHOOK_URL),
                ymlConfig.getString(ConfigPath.KICK_WEBHOOK_TITLE),
                ymlConfig.getString(ConfigPath.KICK_WEBHOOK_THUMBNAIL),
                Integer.parseInt(ymlConfig.getString(ConfigPath.KICK_WEBHOOK_COLOR), 16),
                ymlConfig.getSectionKeys(ConfigPath.KICK_FIELDS_SECTION)
        );

        manager.loadWebhookClients(config);
        return config;
    }

}
