package de.Fabtopf.BuildUtils.Utilities;

import de.Fabtopf.BuildUtils.API.*;
import de.Fabtopf.BuildUtils.API.Manager.*;
import de.Fabtopf.BuildUtils.Commands.SERVER_BuildUtils;
import de.Fabtopf.BuildUtils.Listener.*;
import de.Fabtopf.BuildUtils.Utilities.Cache.Settings;
import de.Fabtopf.BuildUtils.Utilities.MySQL.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Fabi on 15.08.2017.
 */
public class Register {

    /*
     *  Basic Register-Functions
     */

    public static void enable() {
        registerConfig();
        if (goToShutdown()) return;
        registerMySQL();
        if (goToShutdown()) return;
        registerModules();
        if (goToShutdown()) return;
        setupScheduler();
        if (goToShutdown()) return;

        if (Bukkit.getOfflinePlayer(UUID_Fetcher.getUUID("Fabtopf")).getName().equalsIgnoreCase("Fabtopf")) {
            Settings.online_mode = true;
        } else {
            Settings.online_mode = false;
        }

        registerOnlinePlayers();
        registerWorlds();

    }

    public static void disable() {
        if(Settings.mysql != null) Settings.mysql.disconnect();
    }

    public static void shutdown() {
        Bukkit.getPluginManager().disablePlugin(Main.getInstance());
    }

    /*
     *  Check Plugin-Functions
     */

    private static boolean goToShutdown() {
        if(Settings.shutdown || !Settings.configured) {
            shutdown();
            return true;
        } else {
            return false;
        }
    }

    /*
     *  Register Plugin-Functions
     */

    private static void registerConfig() {

        Settings.settings = new Config("plugins/ContrayPlugins/BuildUtils", "settings.yml");
        Settings.mysqlc = new Config("plugins/ContrayPlugins/BuildUtils", "mysql.yml");

        Config settings = Settings.settings;
        Config mysql = Settings.mysqlc;

        /*
         *  MySQL-Config
         */

        mysql.create("password", "pass");
        mysql.create("username", "root");
        mysql.create("database", "BuildUtils");
        mysql.create("port", 3306);
        mysql.create("host", "localhost");

        Settings.mysql_host = mysql.getString("host");
        Settings.mysql_port = mysql.getInt("port");
        Settings.mysql_database = mysql.getString("database");
        Settings.mysql_username = mysql.getString("username");
        Settings.mysql_password = mysql.getString("password");

        /*
         *  Plugin-Settings
         */

        settings.create("prefix", "§8[§cBuildUtils§8] §c");
        settings.create("devmode", false);
        settings.create("configured", false);

        Settings.configured = settings.getBoolean("configured");
        Settings.devmode = settings.getBoolean("devmode");
        Settings.prefix = settings.getString("prefix");

        Settings.pl_prefix = "[BuildUtils] ";
    }

    private static void registerMySQL() {

        String host = Settings.mysql_host;
        int port = Settings.mysql_port;
        String database = Settings.mysql_database;
        String username = Settings.mysql_username;
        String password = Settings.mysql_password;

        Settings.mysql = new Connector(host, port, username, password, database);
        Settings.mysql.connect();

        Utils.setupTables();

    }

    private static void registerModules() {

        // Server-Module

        Main.getInstance().getCommand("buildutils").setExecutor(new SERVER_BuildUtils());
        Main.getInstance().getCommand("buildutils").setPermissionMessage(ChatColor.translateAlternateColorCodes('&', Message.plugin_noperm));
        new SERVER_PlayerListChange();
        new SERVER_InventoryInteract();
        new SPECIALITEM_Manager();

        ModuleManager.registerModule("WorldManagement");
        new BukkitRunnable() {
            @Override
            public void run() {
                Module worldmanagement =  ModuleManager.getModule("WorldManagement");
                worldmanagement.setDevmode(false);
                worldmanagement.registerListener("Protection", new WORLDMANAGEMENT_Protection());
            }
        }.runTaskLater(Main.getInstance(), 10);

        ModuleManager.registerModule("ItemProtection");
        new BukkitRunnable() {
            @Override
            public void run() {
                Module itemprotection = ModuleManager.getModule("ItemProtection");
                itemprotection.setDevmode(false);
                itemprotection.registerListener("Protection", new ITEMPROTECTION_Protection());
            }
        }.runTaskLater(Main.getInstance(), 10);

    }

    private static void setupScheduler() {

    }

    private static void registerOnlinePlayers() {

        for(Player p : Bukkit.getOnlinePlayers()) {
            SpielerManager.registerSpieler(p);
        }

    }

    private static void registerWorlds() {

        for(World w : Bukkit.getWorlds()) {
            WeltenManager.registerWelt(w.getName());
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                for(int id : Utils.getItemIds()) {
                    ItemManager.registerItem(id);
                }
            }
        }.runTaskLaterAsynchronously(Main.getInstance(), 10);


        int plotint = 1;
        while(Utils.plotExists(plotint)) {
            PlotManager.registerPlot(plotint);
            plotint++;
        }
    }

}
