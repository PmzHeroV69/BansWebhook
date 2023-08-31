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
public final class LitebansListener extends Events.Listener {

    private final List<String> allowed = Arrays.asList("ban", "mute", "warn", "kick");
    private final BansWebhook core;

    public static void register(BansWebhook core) {
        Events.get().register(new LitebansListener(core));
    }

    @Override
    public void entryAdded(Entry entry) {
        handleEntry(entry, false);
    }

    @Override
    public void entryRemoved(Entry entry) {
        handleEntry(entry, true);
    }

    private void handleEntry(Entry entry, boolean revoke) {
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
                String duration = entry.getDurationString();
                if (duration.equals("forever")) {
                    duration = config.getPermanentDurationTranslation();
                }

                core.getWebhookManager().sendPunishmentWebhook(executor, entry.getId(), name, entry.getReason(), duration, (revoke ? "un" : "") + type);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
