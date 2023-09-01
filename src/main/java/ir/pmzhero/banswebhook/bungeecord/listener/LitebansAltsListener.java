package ir.pmzhero.banswebhook.bungeecord.listener;

import ir.pmzhero.banswebhook.common.BansWebhook;
import litebans.api.Database;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LitebansAltsListener implements Listener {
    private final BansWebhook core;

    public LitebansAltsListener(BansWebhook core) {
        this.core = core;
    }

    @EventHandler
    public void onPlayerJoin(PostLoginEvent event) {
        String playerIp = event.getPlayer().getSocketAddress().toString();
        if (playerIp == null) return;
        playerIp = playerIp.replace("/", "").split(":")[0];

        Collection<UUID> uuids = Database.get().getUsersByIP(playerIp);
        boolean containsBanned = false;
        for (UUID uuid : uuids) {
            if (Database.get().isPlayerBanned(uuid, null)) containsBanned = true;
        }
        if (!containsBanned || uuids.size() <= 1) return;

        String query = "SELECT name FROM {history} WHERE uuid=? ORDER BY date DESC LIMIT 1";
        List<String> playerNames = uuids.stream().map(uuid -> {
            try (PreparedStatement preparedStatement = Database.get().prepareStatement(query)) {
                preparedStatement.setString(1, uuid.toString());
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    return result.getString("name");
                }

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        core.getWebhookManager().sendAltsWebhook(playerNames);
    }
}
