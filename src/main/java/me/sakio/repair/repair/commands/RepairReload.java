package me.sakio.repair.repair.commands;

import me.sakio.repair.repair.Color;
import me.sakio.repair.repair.Repair;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Project: Repair
 * Date: 19/05/2021 @ 18:31
 * Class: RepairReload
 */
public class RepairReload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("repair.reload") && !(sender instanceof Player)){
            sender.sendMessage(Color.translate("&cYou dont have perms to use this!"));
            return false;
        }
        Repair.getInstance().reloadConfig();
        sender.sendMessage(Color.translate("&cConfig has been reload!"));
        return false;
    }
}
