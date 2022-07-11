package ir.pmzhero.banswebhook.shared;

import ir.pmzhero.banswebhook.shared.data.Config;
import ir.pmzhero.banswebhook.shared.data.ConfigLoader;
import ir.pmzhero.banswebhook.shared.data.YmlConfig;
import ir.pmzhero.banswebhook.shared.webhook.WebhookManager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BansWebhook {

    private Config config;
    private boolean webhooksLoaded = false;
    private final WebhookManager webhookManager = new WebhookManager(this);
    private final YmlConfig ymlConfig;

    public BansWebhook(YmlConfig ymlConfig) {
        this.ymlConfig = ymlConfig;
        config = ConfigLoader.load(ymlConfig, webhookManager);
    }

}
