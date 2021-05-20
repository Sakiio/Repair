package me.sakio.repair.repair.listener;

import me.sakio.repair.repair.Color;
import me.sakio.repair.repair.Repair;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Project: Repair
 * Date: 19/05/2021 @ 18:31
 * Class: RepairListener
 */
public class RepairListener implements Listener {
    @EventHandler
    private void onClickRepair(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            player.sendMessage(Color.translate(Repair.getInstance().getConfig().getString("BAD-CLICK")));
            return;
        }
        if (event.getClickedBlock().getType() != Material.matchMaterial(Repair.getInstance().getConfig().getString("BLOCK-REPAIR"))) {
            return;
        }
        if (Repair.getEcon().getBalance(player) < Repair.getInstance().getConfig().getInt("MONEY")){
            player.sendMessage(Color.translate(Repair.getInstance().getConfig().getString("NO-MONEY")));
            return;
        }

        ItemStack item = player.getItemInHand();

        if (item == null || item.getType() == Material.AIR) {
            player.sendMessage(Color.translate(Repair.getInstance().getConfig().getString("NO-ITEM")));
            return;
        }
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                item.setDurability((short) 0);
                player.sendMessage(Color.translate(Repair.getInstance().getConfig().getString("REPAIR-MESSAGE")));
                Repair.getEcon().withdrawPlayer(player, Repair.getInstance().getConfig().getInt("MONEY"));
                player.setItemInHand(item);
                player.updateInventory();

            }
        };
        task.runTaskLater(Repair.getInstance(), 3*20);
        player.setItemInHand(new ItemStack(Material.AIR));
        player.updateInventory();
    }
}
