package org.arkadst.donationalert;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Main extends JavaPlugin {

    public static FileConfiguration config;
    public static ArrayList<NamespacedKey> namespace_keys;

    static {
        namespace_keys = new ArrayList<>();
    }

    @Override
    public void onEnable() {

        if (!(new File(this.getDataFolder(), "config.yml")).exists()) {
            saveDefaultConfig();
        }

        config = getConfig();

        this.getCommand("announce").setExecutor(new AnnounceCommand(this));
        this.getCommand("donationalert").setExecutor(new DonationAlertCommand(this));
    }

    @Override
    public void onDisable() {
        Iterator<KeyedBossBar> iterator = Bukkit.getBossBars();
        while (iterator.hasNext()){
            NamespacedKey namespaced_key = iterator.next().getKey();
            if (Main.namespace_keys.contains(namespaced_key)){
                Bukkit.removeBossBar(namespaced_key);
            }
        }
    }
}
