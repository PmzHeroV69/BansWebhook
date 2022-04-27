package ir.pmzhero.banswebhook.shared.webhook;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import ir.pmzhero.banswebhook.shared.BansWebhook;
import ir.pmzhero.banswebhook.shared.data.Config;
import ir.pmzhero.banswebhook.shared.data.Pair;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class WebhookManager {

    private final BansWebhook bansWebhook;

    public void sendWebhook(String url, String title, String thumbnail, int color, WebhookEmbed.EmbedField... fields) {

        WebhookClient client = WebhookClient.withUrl(url);
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
        boolean enabled;
        String title;
        String url;
        String thumbnail;
        int color;
        List<Pair<String, String>> pairs;

        switch (type) {
            case "ban":
                enabled = config.isBanEnabled();
                title = config.getBanWebhookTitle();
                url = config.getBanWebhookUrl();
                thumbnail = config.getBanWebhookThumbnail();
                color = config.getBanWebhookColor();
                pairs = config.getBanWebhookFields();
                break;
            case "mute":
                enabled = config.isMuteEnabled();
                title = config.getMuteWebhookTitle();
                url = config.getMuteWebhookUrl();
                thumbnail = config.getMuteWebhookThumbnail();
                color = config.getMuteWebhookColor();
                pairs = config.getMuteWebhookFields();
                break;
            case "warn":
                enabled = config.isWarnEnabled();
                title = config.getWarnWebhookTitle();
                url = config.getWarnWebhookUrl();
                thumbnail = config.getWarnWebhookThumbnail();
                color = config.getWarnWebhookColor();
                pairs = config.getWarnWebhookFields();
                break;
            case "kick":
                enabled = config.isKickEnabled();
                title = config.getKickWebhookTitle();
                url = config.getKickWebhookUrl();
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

        sendWebhook(url, title, thumbnail, color, fields);
    }
}
