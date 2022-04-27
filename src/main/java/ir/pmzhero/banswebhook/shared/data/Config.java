package ir.pmzhero.banswebhook.shared.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class Config {

    private final String reloadMessage;
    private final boolean doNotSendSilent;
    private final boolean inlineWebhooks;

    private final boolean banEnabled;
    private final String banWebhookUrl;
    private final String banWebhookTitle;
    private final String banWebhookThumbnail;
    private final int banWebhookColor;
    private final List<Pair<String, String>> banWebhookFields;

    private final boolean muteEnabled;
    private final String muteWebhookUrl;
    private final String muteWebhookTitle;
    private final String muteWebhookThumbnail;
    private final int muteWebhookColor;
    private final List<Pair<String, String>> muteWebhookFields;

    private final boolean warnEnabled;
    private final String warnWebhookUrl;
    private final String warnWebhookTitle;
    private final String warnWebhookThumbnail;
    private final int warnWebhookColor;
    private final List<Pair<String, String>> warnWebhookFields;

    private final boolean kickEnabled;
    private final String kickWebhookUrl;
    private final String kickWebhookTitle;
    private final String kickWebhookThumbnail;
    private final int kickWebhookColor;
    private final List<Pair<String, String>> kickWebhookFields;

}
