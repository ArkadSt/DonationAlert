package org.arkadst.donationalert;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DonationAlertCommand implements CommandExecutor {

    Main main;

    public DonationAlertCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            switch (args[0]) {
                case "reload":
                    main.reloadConfig();
                    Main.config = main.getConfig();
                    sender.sendMessage(ChatColor.GREEN + "[DonationAlert] Configuration file was reloaded successfully");
                    break;
                default:
                    sender.sendMessage(ChatColor.RED + "[DonationAlert] No such command.");
                    break;
            }

        } else {
            sender.sendMessage(ChatColor.RED + "[DonationAlert] No such command.");
        }
        return true;
    }
}
