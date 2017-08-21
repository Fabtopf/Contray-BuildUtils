package de.Fabtopf.BuildUtils.Listener;

import de.Fabtopf.BuildUtils.API.*;
import de.Fabtopf.BuildUtils.API.Enum.MessagerType;
import de.Fabtopf.BuildUtils.API.Manager.ModuleManager;
import de.Fabtopf.BuildUtils.API.Manager.SpielerManager;
import de.Fabtopf.BuildUtils.API.Manager.WeltenManager;
import de.Fabtopf.BuildUtils.Utilities.Cache.Settings;
import de.Fabtopf.BuildUtils.Utilities.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;

/**
 * Created by Fabi on 15.08.2017.
 */
public class WORLDMANAGEMENT_Protection implements Listener {

    private Module module = ModuleManager.getModule("WorldManagement");

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();
            OfflinePlayer op = Bukkit.getOfflinePlayer(p.getUniqueId());
            Spieler s = SpielerManager.getSpieler(p);
            Welt welt = WeltenManager.getWelt(e.getTo().getWorld().getName());

            if(welt != null && welt.isManaged()) {
                if((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.isLobby() || welt.getBuilders().contains(op) || welt.getCustomers().contains(op)) {
                    return;
                } else {
                    Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToEnter, p);
                    e.setCancelled(true);
                }
            }
        }
    }

    /*
        @EventHandler
        public void onMove(PlayerMoveEvent e) {
            Player p = e.getPlayer();
            OfflinePlayer op = Bukkit.getOfflinePlayer(p.getUniqueId());
            Spieler s = SpielerManager.getSpieler(p);
            Welt welt = WeltenManager.getWelt(e.getTo().getWorld().getName());

            if(welt != null && welt.isManaged()) {
                if((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.isLobby() || welt.getBuilders().contains(op) || welt.getCustomers().contains(op)) {
                    return;
                } else {
                    Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToStayIn, p);
                    e.setCancelled(true);
                }
            }
        }
    */

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();
            OfflinePlayer op = Bukkit.getOfflinePlayer(p.getUniqueId());
            Spieler s = SpielerManager.getSpieler(p);
            Welt welt = WeltenManager.getWelt(p.getLocation().getWorld().getName());

            if (welt != null && welt.isManaged()) {
                if ((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.isLobby() || welt.getBuilders().contains(op) || welt.getCustomers().contains(op)) {
                    if ((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.getBuilders().contains(op)) {
                        return;
                    } else {
                        Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToChangeWorld, p);
                        e.setCancelled(true);
                    }
                } else {
                    Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToChangeWorld, p);
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();
            OfflinePlayer op = Bukkit.getOfflinePlayer(p.getUniqueId());
            Spieler s = SpielerManager.getSpieler(p);
            Welt welt = WeltenManager.getWelt(p.getLocation().getWorld().getName());

            if (welt != null && welt.isManaged()) {
                if ((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.isLobby() || welt.getBuilders().contains(op) || welt.getCustomers().contains(op)) {
                    if ((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.getBuilders().contains(op)) {
                        return;
                    } else {
                        Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToChangeWorld, p);
                        e.setCancelled(true);
                    }
                } else {
                    Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToChangeWorld, p);
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onBucketPlace(PlayerBucketEmptyEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();
            OfflinePlayer op = Bukkit.getOfflinePlayer(p.getUniqueId());
            Spieler s = SpielerManager.getSpieler(p);
            Welt welt = WeltenManager.getWelt(p.getLocation().getWorld().getName());

            if (welt != null && welt.isManaged()) {
                if ((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.isLobby() || welt.getBuilders().contains(op) || welt.getCustomers().contains(op)) {
                    if ((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.getBuilders().contains(op)) {
                        return;
                    } else {
                        Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToChangeWorld, p);
                        e.setCancelled(true);
                    }
                } else {
                    Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToChangeWorld, p);
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();
            OfflinePlayer op = Bukkit.getOfflinePlayer(p.getUniqueId());
            Spieler s = SpielerManager.getSpieler(p);
            Welt welt = WeltenManager.getWelt(p.getLocation().getWorld().getName());

            if (welt != null && welt.isManaged()) {
                if ((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.isLobby() || welt.getBuilders().contains(op) || welt.getCustomers().contains(op)) {
                    if ((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.getBuilders().contains(op)) {
                        return;
                    } else {
                        Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToChangeWorld, p);
                        e.setCancelled(true);
                    }
                } else {
                    Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToChangeWorld, p);
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();
            OfflinePlayer op = Bukkit.getOfflinePlayer(p.getUniqueId());
            Spieler s = SpielerManager.getSpieler(p);
            Welt welt = WeltenManager.getWelt(p.getLocation().getWorld().getName());

            if (welt != null && welt.isManaged()) {
                if ((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.isLobby() || welt.getBuilders().contains(op) || welt.getCustomers().contains(op)) {
                    if ((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.getBuilders().contains(op)) {
                        return;
                    } else {
                        Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToChangeWorld, p);
                        e.setCancelled(true);
                    }
                } else {
                    Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToChangeWorld, p);
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();
            OfflinePlayer op = Bukkit.getOfflinePlayer(p.getUniqueId());
            Spieler s = SpielerManager.getSpieler(p);
            Welt welt = WeltenManager.getWelt(p.getLocation().getWorld().getName());

            if (welt != null && welt.isManaged()) {
                if ((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.isLobby() || welt.getBuilders().contains(op) || welt.getCustomers().contains(op)) {
                    if ((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.getBuilders().contains(op)) {
                        return;
                    } else {
                        Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToChangeWorld, p);
                        e.setCancelled(true);
                    }
                } else {
                    Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToChangeWorld, p);
                    e.setCancelled(true);
                }
            }
        }
    }

}
