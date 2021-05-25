package org.arkadst.donationalert;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AlertCommand implements CommandExecutor {

    Main main;

    AlertCommand(Main main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length >= 2){

            StringBuilder message = new StringBuilder();
            for (int x = 1; x < args.length; x++){
                message.append(args[x]);
                if (x != args.length - 1){
                    message.append(" ");
                }
            }

            NamespacedKey namespaced_key = new NamespacedKey(main, String.valueOf(System.currentTimeMillis()));
            KeyedBossBar boss_bar = Bukkit.createBossBar(namespaced_key, message.toString(), BarColor.valueOf(args[0]), BarStyle.SOLID);
            boss_bar.setProgress(1.0);
            Main.namespace_keys.add(namespaced_key);
            for (Player player : Bukkit.getOnlinePlayers()){
                boss_bar.addPlayer(player);
            }
            Bukkit.getScheduler().runTaskLater(main, () -> {
                boss_bar.removeAll();
                Bukkit.removeBossBar(namespaced_key);
                Main.namespace_keys.remove(namespaced_key);
            }, Main.config.getLong("bossbar_lifespan") * 20L);
            return true;
        }
        return false;
    }
}
