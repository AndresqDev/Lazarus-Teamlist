package volcanic.tab.lazarus;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import volcanic.tab.lazarus.listeners.PlayerListener;
import volcanic.tab.lazarus.managers.TeamlistManager;
import volcanic.tab.lazarus.utils.CC;
import volcanic.tab.lazarus.utils.provider.Data;

public class Main extends JavaPlugin {
    public static Main plugin;
    public static BukkitScheduler scheduler;

    @Override
    public void onEnable() {
        plugin = this;
        scheduler = Bukkit.getScheduler();

        this.setupConfig();
        this.setupManagers();
        this.setupListeners();

        final ConsoleCommandSender cc = Bukkit.getConsoleSender();
        cc.sendMessage(CC.translate("&3===&b=============================================&3==="));
        cc.sendMessage(CC.translate("&4Plugin name: &f" + this.getName()));
        cc.sendMessage(" ");
        cc.sendMessage(CC.translate("&4Plugin authors: &f" + this.getDescription().getAuthors()));
        cc.sendMessage(CC.translate("&4Support: &ahttps://discord.gg/jr4A3y3nzq"));
        cc.sendMessage(" ");
        cc.sendMessage(CC.translate("&aPlugin loaded!"));
        cc.sendMessage(CC.translate("&3===&b=============================================&3==="));
    }

    @Override
    public void onDisable() {
        //Ignored
    }

    private void setupConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        Data.set();
    }

    private void setupManagers() {
        new TeamlistManager();
    }

    private void setupListeners() {
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerListener(), this);
    }
}