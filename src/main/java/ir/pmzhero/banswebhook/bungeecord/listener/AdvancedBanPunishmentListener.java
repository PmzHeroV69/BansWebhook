package ir.pmzhero.banswebhook.bungeecord.listener;

import ir.pmzhero.banswebhook.shared.BansWebhook;
import ir.pmzhero.banswebhook.shared.advancedban.AdvancedBanEventHandler;
import lombok.RequiredArgsConstructor;
import me.leoko.advancedban.bungee.event.PunishmentEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

@RequiredArgsConstructor
public class AdvancedBanPunishmentListener implements Listener {

    private final BansWebhook core;

    @EventHandler
    public void onPunishment(PunishmentEvent event) {
        AdvancedBanEventHandler.handle(event.getPunishment(), core);
    }

}
