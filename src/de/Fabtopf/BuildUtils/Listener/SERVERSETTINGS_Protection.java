package de.Fabtopf.BuildUtils.Listener;

import de.Fabtopf.BuildUtils.API.Converter;
import de.Fabtopf.BuildUtils.API.CustomPerm;
import de.Fabtopf.BuildUtils.API.Manager.ModuleManager;
import de.Fabtopf.BuildUtils.API.Manager.PermissionManager;
import de.Fabtopf.BuildUtils.API.Manager.WeltenManager;
import de.Fabtopf.BuildUtils.API.Module;
import de.Fabtopf.BuildUtils.API.Welt;
import de.Fabtopf.BuildUtils.Utilities.Cache.Settings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Created by Fabi on 28.08.2017.
 */
public class SERVERSETTINGS_Protection implements Listener {

    Module module = ModuleManager.getModule("ServerSettings");

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();
            Welt welt = null;
            if(WeltenManager.getWelt(p.getWorld().getName()) != null) welt = WeltenManager.getWelt(p.getWorld().getName());

            if(welt != null && welt.isManaged() && !Settings.serversettings_drop) {
                if (PermissionManager.check(p, new CustomPerm("contray.buildutils.serversettings.exempt", Converter.stringToArray("contray.buildutils.serversettings.*", "contray.buildutils.*", "contray.*"), true, true))) {
                    return;
                } else {
                    e.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();
            Welt welt = null;
            if(WeltenManager.getWelt(p.getWorld().getName()) != null) welt = WeltenManager.getWelt(p.getWorld().getName());

            if(welt != null && welt.isManaged() && !Settings.serversettings_pickup) {
                if (PermissionManager.check(p, new CustomPerm("contray.buildutils.serversettings.exempt", Converter.stringToArray("contray.buildutils.serversettings.*", "contray.buildutils.*", "contray.*"), true, true))) {
                    return;
                } else {
                    e.setCancelled(true);
                    return;
                }
            }
        }
    }

}
