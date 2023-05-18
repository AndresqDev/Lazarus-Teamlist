package volcanic.tab.lazarus.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import volcanic.tab.lazarus.managers.TeamlistManager;

import static volcanic.tab.lazarus.Main.plugin;
import static volcanic.tab.lazarus.Main.scheduler;

public class PlayerListener implements Listener {
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        scheduler.runTaskLater(plugin, TeamlistManager::update, 75);
    }
}
