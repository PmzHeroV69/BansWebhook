package ir.pmzhero.banswebhook.common.litebans;

import ir.pmzhero.banswebhook.common.BansWebhook;
import ir.pmzhero.banswebhook.common.data.Config;
import litebans.api.Database;
import litebans.api.Entry;
import litebans.api.Events;
import lombok.RequiredArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class LitebansListener extends Events.Listener {

    private final List<String> allowed = Arrays.asList("ban", "mute", "warn", "kick");
    private final BansWebhook core;

    public static void register(BansWebhook core) {
        Events.get().register(new LitebansListener(core));
    }

    @Override
    public void entryAdded(Entry entry) {

        String type = entry.getType();
        Config config = core.getConfig();

        if (!allowed.contains(type)) {
            return;
        }

        if (entry.isSilent() && config.isDoNotSendSilent()) {
            return;
        }

        String query = "SELECT name FROM {history} WHERE uuid=? ORDER BY date DESC LIMIT 1";

        try (PreparedStatement preparedStatement = Database.get().prepareStatement(query)) {
            preparedStatement.setString(1, entry.getUuid());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {

                String executor = entry.getExecutorName();
                if (executor == null) {
                    executor = "Unknown";
                }
                String name = result.getString("name");

                core.getWebhookManager().sendPunishmentWebhook(executor, name, entry.getReason(), entry.getDurationString(), entry.getType());
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
}
