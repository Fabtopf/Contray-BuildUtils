package de.Fabtopf.BuildUtils.Listener;

import de.Fabtopf.BuildUtils.API.Manager.SpielerManager;
import de.Fabtopf.BuildUtils.Utilities.Main;
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

        SpielerManager.registerSpieler(p);
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        SpielerManager.unregisterSpieler(p);
    }

}
