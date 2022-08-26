package ir.pmzhero.banswebhook.common.webhook;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import ir.pmzhero.banswebhook.common.BansWebhook;
import ir.pmzhero.banswebhook.common.data.Config;
import ir.pmzhero.banswebhook.common.data.Pair;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class WebhookManager {

    private final BansWebhook bansWebhook;

    private WebhookClient banClient;
    private WebhookClient muteClient;
    private WebhookClient warnClient;
    private WebhookClient kickClient;

    public void sendWebhook(WebhookClient client, String title, String thumbnail, int color, WebhookEmbed.EmbedField... fields) {

        WebhookEmbedBuilder builder = new WebhookEmbedBuilder()
                .setTitle(new WebhookEmbed.EmbedTitle(title, ""))
                .setThumbnailUrl(thumbnail)
                .setColor(color);
        for (WebhookEmbed.EmbedField field : fields) {
            builder.addField(field);
        }

        client.send(builder.build());
    }

    public void sendPunishmentWebhook(String executor, String name, String reason, String duration, String type) {

        Config config = bansWebhook.getConfig();
        WebhookClient client;

        boolean enabled;
        String title;
        String thumbnail;
        int color;
        List<Pair<String, String>> pairs;

        switch (type) {
            case "ban":
                client = banClient;
                enabled = config.isBanEnabled();
                title = config.getBanWebhookTitle();
                thumbnail = config.getBanWebhookThumbnail();
                color = config.getBanWebhookColor();
                pairs = config.getBanWebhookFields();
                break;
            case "mute":
                client = muteClient;
                enabled = config.isMuteEnabled();
                title = config.getMuteWebhookTitle();
                thumbnail = config.getMuteWebhookThumbnail();
                color = config.getMuteWebhookColor();
                pairs = config.getMuteWebhookFields();
                break;
            case "warn":
                client = warnClient;
                enabled = config.isWarnEnabled();
                title = config.getWarnWebhookTitle();
                thumbnail = config.getWarnWebhookThumbnail();
                color = config.getWarnWebhookColor();
                pairs = config.getWarnWebhookFields();
                break;
            case "kick":
                client = kickClient;
                enabled = config.isKickEnabled();
                title = config.getKickWebhookTitle();
                thumbnail = config.getKickWebhookThumbnail();
                color = config.getKickWebhookColor();
                pairs = config.getKickWebhookFields();
                break;
            default:
                return;
        }

        if (!enabled) {
            return;
        }

        WebhookEmbed.EmbedField[] fields = new WebhookEmbed.EmbedField[pairs.size()];

        int i = -1;
        for (Pair<String, String> pair : pairs) {
            i++;
            String value = pair.getValue()
                    .replace("{executor}", executor)
                    .replace("{player}", name)
                    .replace("{reason}", reason)
                    .replace("{duration}", duration);

            fields[i] = new WebhookEmbed.EmbedField(config.isInlineWebhooks(), pair.getKey(), value);
        }

        title = title
                .replace("{executor}", executor)
                .replace("{player}", name)
                .replace("{reason}", reason)
                .replace("{duration}", duration);

        sendWebhook(client, title, thumbnail, color, fields);
    }

    public void loadWebhookClients(Config config) {
        try {
            this.banClient = WebhookClient.withUrl(config.getBanWebhookUrl());
            this.muteClient = WebhookClient.withUrl(config.getMuteWebhookUrl());
            this.warnClient = WebhookClient.withUrl(config.getWarnWebhookUrl());
            this.kickClient = WebhookClient.withUrl(config.getKickWebhookUrl());
            bansWebhook.setWebhooksLoaded(true);
        } catch (Exception ignored) {
            bansWebhook.setWebhooksLoaded(false);
        }
    }
}
