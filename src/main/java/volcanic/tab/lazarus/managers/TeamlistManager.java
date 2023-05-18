package volcanic.tab.lazarus.managers;

import me.qiooip.lazarus.factions.type.PlayerFaction;
import me.qiooip.lazarus.tab.nms.PlayerTab_1_8;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import static volcanic.tab.lazarus.Main.scheduler;
import static volcanic.tab.lazarus.utils.provider.Data.*;

public class TeamlistManager {
    public TeamlistManager() {
        this.startTasks();
    }

    public static void update() {
        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            factionList = factionsManager.getFactions().values().stream().filter(x -> x instanceof PlayerFaction).map(x -> (PlayerFaction) x).filter(x -> (x.getOnlinePlayers().size() > 0)).distinct().sorted(Comparator.comparingInt(playerFactions -> playerFactions.getOnlinePlayers().size())).collect(Collectors.toList());
            Collections.reverse(factionList);
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (via.getPlayerVersion(p.getUniqueId()) != 47) {
                    continue;
                }
                PlayerTab_1_8 tab = (PlayerTab_1_8) lazarusInstance.getTabManager().getTab(p);
                tab.set(START_SLOT, TEAMLIST_NAME);
                for (int i = 0; i <= NUMBER_OF_TEAMS; i++) {
                    tab.set(TEAMS_START_SLOT + i, createFactionList(p, i));
                }
            }
        }
    }

    public static String createFactionList(Player player, int i) { //This part of the code was reused and modified to save time, Credits: aapy.
        if (i >= factionList.size()) {
            return " ";
        }
        final PlayerFaction next = factionList.get(i);
        final String name = next.getName(player);
        return PLACEHOLDER
                .replace("<number>", "#" + (i + 1))
                .replace("<members>", next.getOnlinePlayers().size() + "")
                .replace("<dtr>", next.getDtrString())
                .replace("<team>", (name.length() > 10) ? name.substring(0, 10) : name);
    }

    private void startTasks() {
        scheduler.runTaskTimerAsynchronously(lazarusInstance, TeamlistManager::update, 40, TEAMLIST_UPDATE_INTERVAL);
    }
}
