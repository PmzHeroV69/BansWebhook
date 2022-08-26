package ir.pmzhero.banswebhook.spigot;

import ir.pmzhero.banswebhook.common.BansWebhook;
import ir.pmzhero.banswebhook.common.litebans.LitebansListener;
import ir.pmzhero.banswebhook.spigot.command.CommandMainSpigot;
import ir.pmzhero.banswebhook.spigot.data.SpigotYmlConfig;
import ir.pmzhero.banswebhook.spigot.listener.AdvancedBanPunishmentListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@Getter
public class BansWebhookSpigot extends JavaPlugin {

    private BansWebhook core;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        core = new BansWebhook(new SpigotYmlConfig(this));

        PluginManager manager = Bukkit.getPluginManager();
        Logger logger = getLogger();

        String hook;

        if (manager.isPluginEnabled("LiteBans")) {
            LitebansListener.register(core);
            hook = "LiteBans";
        } else if (manager.isPluginEnabled("AdvancedBan")) {
            manager.registerEvents(new AdvancedBanPunishmentListener(core), this);
            hook = "AdvancedBan";
        } else {
            logger.severe("Failed to enable BansWebhook! there is no ban plugin for BansWebhook to work with!");
            manager.disablePlugin(this);
            return;
        }

        getCommand("bwh").setExecutor(new CommandMainSpigot(core));

        if (core.isWebhooksLoaded()) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Successfully Enabled BansWebhook " + ChatColor.GRAY + "$(version)" + ChatColor.GREEN + " by PmzHero (Hooked with " + hook + ")");
        } else {
            getLogger().severe("Webhooks can't be loaded!");
        }
    }
}
