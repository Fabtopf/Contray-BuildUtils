package de.Fabtopf.BuildUtils.Listener;

import de.Fabtopf.BuildUtils.API.FinishedPlot;
import de.Fabtopf.BuildUtils.API.Manager.PlotManager;
import de.Fabtopf.BuildUtils.API.Manager.SpielerManager;
import de.Fabtopf.BuildUtils.API.Manager.WeltenManager;
import de.Fabtopf.BuildUtils.API.Spieler;
import de.Fabtopf.BuildUtils.Utilities.Cache.Settings;
import de.Fabtopf.BuildUtils.Utilities.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Fabi on 15.08.2017.
 */
public class SERVER_PlayerListChange implements Listener {

    public SERVER_PlayerListChange() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if(WeltenManager.getWelt(p.getWorld().getName()) != null && WeltenManager.getWelt(p.getWorld().getName()).isManaged()) {
            p.setGameMode(GameMode.getByValue(WeltenManager.getWelt(p.getWorld().getName()).getGamemode()));
        } else {
            p.setGameMode(GameMode.getByValue(Settings.serversettings_gamemode));
        }

        SpielerManager.registerSpieler(p);
        if(PlotManager.getPlot(Bukkit.getOfflinePlayer(p.getUniqueId())) != null) {
            FinishedPlot plot = PlotManager.getPlot(Bukkit.getOfflinePlayer(p.getUniqueId()));
            if(plot.getPlotState().getRated() == true && plot.isNoticed() == false) {
                switch(plot.getPlotState().name()) {
                    case "bad":
                        plot.setNoticed(true);
                        p.sendTitle("§9§lInfo zu deinem Plot", "§cDein Plot wurde abgelehnt!");
                        break;

                    case "good":
                        plot.setNoticed(true);
                        Bukkit.getPlayer(plot.getPlayer().getUniqueId()).sendTitle("§9§lInfo zu deinem Plot", "§6Dein Plot kann noch verbessert werden!");
                        break;

                    case "accepted":
                        plot.setNoticed(true);
                        Bukkit.getPlayer(plot.getPlayer().getUniqueId()).sendTitle("§9§lInfo zu deinem Plot", "§aDein Plot wurde angenommen!");
                        break;
                }
            }
        }
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if(WeltenManager.getWelt(p.getWorld().getName()) == null) WeltenManager.registerWelt(p.getWorld().getName());
        if(WeltenManager.getWelt(p.getWorld().getName()).isManaged()) p.getInventory().clear();

        SpielerManager.unregisterSpieler(p);
    }

}
