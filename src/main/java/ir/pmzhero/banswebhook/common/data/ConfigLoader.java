package ir.pmzhero.banswebhook.common.data;

import ir.pmzhero.banswebhook.common.webhook.WebhookManager;

public final class ConfigLoader {

    public static Config load(YmlConfig ymlConfig, WebhookManager manager) {
        Config config = new Config(
                ymlConfig.getString(ConfigPath.RELOAD_MESSAGE),
                ymlConfig.getString(ConfigPath.PERMANENT_DURATION_TRANSLATION),
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
                ymlConfig.getSectionKeys(ConfigPath.KICK_FIELDS_SECTION),

                ymlConfig.getBoolean(ConfigPath.UNBAN_ENABLED),
                ymlConfig.getString(ConfigPath.UNBAN_WEBHOOK_URL),
                ymlConfig.getString(ConfigPath.UNBAN_WEBHOOK_TITLE),
                ymlConfig.getString(ConfigPath.UNBAN_WEBHOOK_THUMBNAIL),
                Integer.parseInt(ymlConfig.getString(ConfigPath.UNBAN_WEBHOOK_COLOR), 16),
                ymlConfig.getSectionKeys(ConfigPath.UNBAN_FIELDS_SECTION),

                ymlConfig.getBoolean(ConfigPath.UNMUTE_ENABLED),
                ymlConfig.getString(ConfigPath.UNMUTE_WEBHOOK_URL),
                ymlConfig.getString(ConfigPath.UNMUTE_WEBHOOK_TITLE),
                ymlConfig.getString(ConfigPath.UNMUTE_WEBHOOK_THUMBNAIL),
                Integer.parseInt(ymlConfig.getString(ConfigPath.UNMUTE_WEBHOOK_COLOR), 16),
                ymlConfig.getSectionKeys(ConfigPath.UNMUTE_FIELDS_SECTION),

                ymlConfig.getBoolean(ConfigPath.UNWARN_ENABLED),
                ymlConfig.getString(ConfigPath.UNWARN_WEBHOOK_URL),
                ymlConfig.getString(ConfigPath.UNWARN_WEBHOOK_TITLE),
                ymlConfig.getString(ConfigPath.UNWARN_WEBHOOK_THUMBNAIL),
                Integer.parseInt(ymlConfig.getString(ConfigPath.UNWARN_WEBHOOK_COLOR), 16),
                ymlConfig.getSectionKeys(ConfigPath.UNWARN_FIELDS_SECTION)
        );

        manager.loadWebhookClients(config);
        return config;
    }

}
