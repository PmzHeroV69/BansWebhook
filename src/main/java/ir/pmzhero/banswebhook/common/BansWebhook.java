package ir.pmzhero.banswebhook.common;

import ir.pmzhero.banswebhook.common.data.Config;
import ir.pmzhero.banswebhook.common.data.ConfigLoader;
import ir.pmzhero.banswebhook.common.data.YmlConfig;
import ir.pmzhero.banswebhook.common.webhook.WebhookManager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class BansWebhook {

    private final WebhookManager webhookManager = new WebhookManager(this);
    private final YmlConfig ymlConfig;
    private Config config;
    private boolean webhooksLoaded = false;

    public BansWebhook(YmlConfig ymlConfig) {
        this.ymlConfig = ymlConfig;
        config = ConfigLoader.load(ymlConfig, webhookManager);
    }

}
