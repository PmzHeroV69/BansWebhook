package ir.pmzhero.banswebhook.spigot.listener;

import ir.pmzhero.banswebhook.common.BansWebhook;
import ir.pmzhero.banswebhook.common.advancedban.AdvancedBanEventHandler;
import lombok.RequiredArgsConstructor;
import me.leoko.advancedban.bukkit.event.PunishmentEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class AdvancedBanPunishmentListener implements Listener {

    private final BansWebhook core;

    @EventHandler
    public void onPunishment(PunishmentEvent event) {
        AdvancedBanEventHandler.handle(event.getPunishment(), core);
    }

}
