package me.sakio.repair.repair;

import lombok.Getter;
import me.sakio.repair.repair.commands.ItemCommand;
import me.sakio.repair.repair.commands.RepairReload;
import me.sakio.repair.repair.listener.RepairListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Repair extends JavaPlugin {
    @Getter private static Repair instance;
    @Getter private static Economy econ = null;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        reloadConfig();

        if (!setupEconomy() ) {
            Bukkit.getConsoleSender().sendMessage(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new RepairListener(), getInstance());

        this.getCommand("rreload").setExecutor(new RepairReload());
        this.getCommand("ritem").setExecutor(new ItemCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
