package ir.pmzhero.banswebhook.common.advancedban;

import ir.pmzhero.banswebhook.common.BansWebhook;
import me.leoko.advancedban.utils.Punishment;
import me.leoko.advancedban.utils.PunishmentType;

public class AdvancedBanEventHandler {

    public static void handle(Punishment punishment, BansWebhook core) {
        PunishmentType type = punishment.getType();

        String executor = punishment.getOperator();
        String name = punishment.getName();
        String reason = punishment.getReason();
        String duration = punishment.getDuration(true);

        String punishType = null;
        if (type == PunishmentType.BAN || type == PunishmentType.TEMP_BAN || type == PunishmentType.IP_BAN || type == PunishmentType.TEMP_IP_BAN) {
            punishType = "ban";
        } else if (type == PunishmentType.WARNING || type == PunishmentType.TEMP_WARNING) {
            punishType = "warn";
        } else if (type == PunishmentType.KICK) {
            punishType = "kick";
        } else if (type == PunishmentType.MUTE || type == PunishmentType.TEMP_MUTE) {
            punishType = "mute";
        }

        if (punishType != null) {
            core.getWebhookManager().sendPunishmentWebhook(executor, name, reason, duration, punishType);
        }
    }
}
