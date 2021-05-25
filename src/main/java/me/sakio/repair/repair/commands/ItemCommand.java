package me.sakio.repair.repair.commands;

import me.sakio.repair.repair.Color;
import me.sakio.repair.repair.Repair;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Project: Repair
 * Date: 25/05/2021 @ 19:59
 * Class: ItemCommand
 */
public class ItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission("ralixrepair.item")) {
            Material itemType = Material.matchMaterial(Repair.getInstance().getConfig().getString("BLOCK-REPAIR"));
            ItemStack itemStack = new ItemStack(itemType);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(Color.translate(Repair.getInstance().getConfig().getString("BLOCK-NAME")));
            itemStack.setItemMeta(itemMeta);
            player.getInventory().addItem(itemStack);
            player.sendMessage(ChatColor.RED + "Here you go.");

        } else {
            player.sendMessage(ChatColor.RED + "You don't have permissions.");
        }
        return true;
    }
}
