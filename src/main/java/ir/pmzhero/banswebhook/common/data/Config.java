package ir.pmzhero.banswebhook.common.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public final class Config {

    private final String reloadMessage;
    private final String permanentDurationTranslation;
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

    private final boolean unbanEnabled;
    private final String unbanWebhookUrl;
    private final String unbanWebhookTitle;
    private final String unbanWebhookThumbnail;
    private final int unbanWebhookColor;
    private final List<Pair<String, String>> unbanWebhookFields;

    private final boolean unmuteEnabled;
    private final String unmuteWebhookUrl;
    private final String unmuteWebhookTitle;
    private final String unmuteWebhookThumbnail;
    private final int unmuteWebhookColor;
    private final List<Pair<String, String>> unmuteWebhookFields;

    private final boolean unwarnEnabled;
    private final String unwarnWebhookUrl;
    private final String unwarnWebhookTitle;
    private final String unwarnWebhookThumbnail;
    private final int unwarnWebhookColor;
    private final List<Pair<String, String>> unwarnWebhookFields;

    private final boolean altsEnabled;
    private final String altsWebhookUrl;
    private final String altsWebhookTitle;
    private final String altsWebhookThumbnail;
    private final int altsWebhookColor;
    private final List<Pair<String, String>> altsWebhookFields;
}
