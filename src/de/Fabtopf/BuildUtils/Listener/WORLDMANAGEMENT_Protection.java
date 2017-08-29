package de.Fabtopf.BuildUtils.Listener;

import de.Fabtopf.BuildUtils.API.*;
import de.Fabtopf.BuildUtils.API.Enum.MessagerType;
import de.Fabtopf.BuildUtils.API.Enum.PlotState;
import de.Fabtopf.BuildUtils.API.Manager.*;
import de.Fabtopf.BuildUtils.Utilities.Cache.Settings;
import de.Fabtopf.BuildUtils.Utilities.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Fabi on 15.08.2017.
 */
public class WORLDMANAGEMENT_Protection implements Listener {

    private Module module = ModuleManager.getModule("WorldManagement");

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();

            if(e.getFrom().getWorld() != e.getTo().getWorld()) {
                if(WeltenManager.getWelt(e.getTo().getWorld().getName()) != null && WeltenManager.getWelt(e.getTo().getWorld().getName()).isManaged()) {
                    p.setGameMode(GameMode.getByValue(WeltenManager.getWelt(e.getTo().getWorld().getName()).getGamemode()));
                } else {
                    p.setGameMode(GameMode.getByValue(Settings.serversettings_gamemode));
                }
            }

            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.worldmanagement.exempt", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.worldmanagement.*"), false, true))) {
                return;
            } else {
                OfflinePlayer op = Bukkit.getOfflinePlayer(p.getUniqueId());
                Spieler s = SpielerManager.getSpieler(p);
                Welt welt = WeltenManager.getWelt(e.getTo().getWorld().getName());

                if (welt != null && welt.isManaged()) {
                    if ((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.isLobby() || welt.getBuilders().contains(op) || welt.getCustomers().contains(op)) {
                        if(e.getFrom().getWorld() != e.getTo().getWorld()) {
                            for(int i = 0; i < 40; i++) {
                                int item = p.getInventory().getItem(i).getTypeId();
                                if(ItemManager.getItem(item) != null && !ItemManager.getItem(item).isInventory() && p.getInventory().getItem(i).getItemMeta().getDisplayName() == null) {
                                    p.getInventory().setItem(i, null);
                                }
                            }
                        }
                        return;
                    } else {
                        Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToEnter, p);
                        e.setCancelled(true);
                    }
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

            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.worldmanagement.exempt", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.worldmanagement.*"), false, true))) {
                return;
            } else {
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

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();

            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.worldmanagement.exempt", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.worldmanagement.*"), false, true))) {
                return;
            } else {
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

    @EventHandler
    public void onBucketPlace(PlayerBucketEmptyEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();

            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.worldmanagement.exempt", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.worldmanagement.*"), false, true))) {
                return;
            } else {
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

    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();

            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.worldmanagement.exempt", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.worldmanagement.*"), false, true))) {
                return;
            } else {
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

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();

            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.worldmanagement.exempt", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.worldmanagement.*"), false, true))) {
                return;
            } else {
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

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();

            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.worldmanagement.exempt", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.worldmanagement.*"), false, true))) {
                return;
            } else {
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

    @EventHandler
    public void onPlayerDamagePlayer(EntityDamageByEntityEvent e) {

        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            if(e.getDamager().getType() == EntityType.PLAYER && e.getEntity().getType() == EntityType.PLAYER) {
                Player p = (Player) e.getDamager();
                Welt welt = WeltenManager.getWelt(p.getLocation().getWorld().getName());

                if(welt != null && welt.isManaged() && welt.isLobby()) {
                    e.setCancelled(true);
                }
            }
        }

    }

    @EventHandler
    public void onPlayerTakeDamage(EntityDamageEvent e) {

        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            if (e.getEntity().getType() == EntityType.PLAYER) {
                if (WeltenManager.getWelt(e.getEntity().getLocation().getWorld().getName()) != null && WeltenManager.getWelt(e.getEntity().getLocation().getWorld().getName()).isManaged() && WeltenManager.getWelt(e.getEntity().getLocation().getWorld().getName()).isLobby()) {
                    e.setCancelled(true);
                }
            }
        }

    }

    @EventHandler
    public void onPlayerGetHunger(FoodLevelChangeEvent e) {

        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            if (WeltenManager.getWelt(e.getEntity().getLocation().getWorld().getName()) != null && WeltenManager.getWelt(e.getEntity().getLocation().getWorld().getName()).isManaged() && WeltenManager.getWelt(e.getEntity().getLocation().getWorld().getName()).isLobby()) {
                e.setCancelled(true);
            }
        }

    }

    /*
     *  Wenn PlotRating aktiv ist
     */

    @EventHandler
    public void onInteractWithRateItem(PlayerInteractEvent e) {
        Spieler s = SpielerManager.getSpieler(e.getPlayer());
        if(s.isRate()) {
            e.setCancelled(true);

            FinishedPlot plot = PlotManager.getPlot(Bukkit.getOfflinePlayer(ChatColor.stripColor(e.getPlayer().getInventory().getItem(22).getItemMeta().getDisplayName())));

            if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
                if (e.getItem().getItemMeta().getDisplayName().equals("§cSchlecht")) {
                    plot.updatePlotState(PlotState.bad);
                    s.getP().getInventory().clear();
                    s.setRate(false);

                    if(plot.getPlayer().isOnline()) {
                        plot.setNoticed(true);
                        Bukkit.getPlayer(plot.getPlayer().getUniqueId()).sendTitle("§9§lInfo zu deinem Plot", "§cDein Plot wurde abgelehnt!");
                    }
                }

                if (e.getItem().getItemMeta().getDisplayName().equals("§6Weiterbauen")) {
                    plot.updatePlotState(PlotState.good);
                    s.getP().getInventory().clear();
                    s.setRate(false);

                    if(plot.getPlayer().isOnline()) {
                        plot.setNoticed(true);
                        Bukkit.getPlayer(plot.getPlayer().getUniqueId()).sendTitle("§9§lInfo zu deinem Plot", "§6Dein Plot kann noch verbessert werden!");
                    }
                }

                if (e.getItem().getItemMeta().getDisplayName().equals("§aAngenommen")) {
                    plot.updatePlotState(PlotState.accepted);
                    s.getP().getInventory().clear();
                    s.setRate(false);

                    if(plot.getPlayer().isOnline()) {
                        plot.setNoticed(true);
                        Bukkit.getPlayer(plot.getPlayer().getUniqueId()).sendTitle("§9§lInfo zu deinem Plot", "§aDein Plot wurde angenommen!");
                    }
                }

                if (e.getItem().getItemMeta().getDisplayName().equals("§cAbbrechen")) {
                    s.getP().getInventory().clear();
                    s.setRate(false);
                }

            }
        }

    }

    @EventHandler
    public void onInventoryInteractWhileRating(InventoryClickEvent e) {
        Spieler s = SpielerManager.getSpieler((Player) e.getWhoClicked());
        if(s.isRate()) {
            e.setCancelled(true);
        }
    }

}
