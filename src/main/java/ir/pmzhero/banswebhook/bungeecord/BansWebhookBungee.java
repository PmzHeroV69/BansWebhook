package ir.pmzhero.banswebhook.bungeecord;

import ir.pmzhero.banswebhook.bungeecord.command.MainCommandBungee;
import ir.pmzhero.banswebhook.bungeecord.data.BungeeConfigFile;
import ir.pmzhero.banswebhook.bungeecord.data.BungeeYmlConfig;
import ir.pmzhero.banswebhook.bungeecord.listener.AdvancedBanPunishmentListener;
import ir.pmzhero.banswebhook.common.BansWebhook;
import ir.pmzhero.banswebhook.common.litebans.LitebansListener;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public final class BansWebhookBungee extends Plugin {

    @Override
    public void onEnable() {
        BungeeConfigFile configFile = new BungeeConfigFile(this, "config.yml");
        BansWebhook core = new BansWebhook(new BungeeYmlConfig(configFile));

        PluginManager manager = getProxy().getPluginManager();

        String hook;

        if (manager.getPlugin("LiteBans") != null) {
            hook = "LiteBans";
            LitebansListener.register(core);
        } else if (manager.getPlugin("AdvancedBan") != null) {
            hook = "AdvancedBan";
            manager.registerListener(this, new AdvancedBanPunishmentListener(core));
        } else {
            getLogger().severe("Failed to enable BansWebhook! there is no ban plugin for BansWebhook to work with!");
            return;
        }

        manager.registerCommand(this, new MainCommandBungee(core));
        if (core.isWebhooksLoaded()) {
            getProxy().getConsole().sendMessage(TextComponent.fromLegacyText(ChatColor.GREEN + "Successfully Enabled BansWebhook " + ChatColor.GRAY + "$(version)" + ChatColor.GREEN + " by PmzHero (Hooked with " + hook + ")"));
        } else {
            getLogger().severe("Webhooks can't be loaded!");
        }
    }
}
