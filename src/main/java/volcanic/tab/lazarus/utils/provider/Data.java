package volcanic.tab.lazarus.utils.provider;

import me.qiooip.lazarus.Lazarus;
import me.qiooip.lazarus.factions.FactionsManager;
import me.qiooip.lazarus.factions.type.PlayerFaction;
import org.bukkit.configuration.file.FileConfiguration;
import us.myles.ViaVersion.api.Via;
import us.myles.ViaVersion.api.ViaAPI;
import volcanic.tab.lazarus.utils.CC;

import java.util.List;

import static volcanic.tab.lazarus.Main.plugin;

public class Data {
    @SuppressWarnings("all")
    public static ViaAPI via;
    public static Lazarus lazarusInstance;
    public static FactionsManager factionsManager;
    public static List<PlayerFaction> factionList;

    public static String TEAMLIST_NAME;
    public static String PLACEHOLDER;
    public static int START_SLOT;
    public static int TEAMS_START_SLOT;
    public static int NUMBER_OF_TEAMS;
    public static int TEAMLIST_UPDATE_INTERVAL;

    public static void set() {
        final FileConfiguration currentConfigContent = plugin.getConfig();

        //Internal
        via = Via.getAPI();
        lazarusInstance = Lazarus.getInstance();
        factionsManager = FactionsManager.getInstance();

        //Config
        TEAMLIST_NAME = CC.translate(currentConfigContent.getString("TEAMLIST_NAME"));
        PLACEHOLDER = CC.translate(currentConfigContent.getString("PLACEHOLDER"));
        START_SLOT = currentConfigContent.getInt("START_SLOT");
        TEAMS_START_SLOT = START_SLOT + 1;
        NUMBER_OF_TEAMS = currentConfigContent.getInt("NUMBER_OF_TEAMS");
        TEAMLIST_UPDATE_INTERVAL = currentConfigContent.getInt("TEAMLIST_UPDATE_INTERVAL");
    }
}