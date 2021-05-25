package org.arkadst.donationalert;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Iterator<KeyedBossBar> iterator = Bukkit.getBossBars();
        while (iterator.hasNext()){
            NamespacedKey namespaced_key = iterator.next().getKey();
            if (Main.namespace_keys.contains(namespaced_key)){
                Bukkit.getBossBar(namespaced_key).addPlayer(event.getPlayer());
            }
        }
    }

}
