package de.Fabtopf.BuildUtils.Listener;

import de.Fabtopf.BuildUtils.API.*;
import de.Fabtopf.BuildUtils.API.Enum.MessagerType;
import de.Fabtopf.BuildUtils.API.Manager.PermissionManager;
import de.Fabtopf.BuildUtils.API.Manager.SpielerManager;
import de.Fabtopf.BuildUtils.Commands.Inventory.INV_BuildUtils;
import de.Fabtopf.BuildUtils.Utilities.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Fabi on 25.08.2017.
 */
public class SPECIALITEM_Manager implements Listener {

    public SPECIALITEM_Manager() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryInteract(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Spieler s = SpielerManager.getSpieler(p);

        if(e.getClickedInventory() == null || e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
            return;
        }


        if((e.getInventory().getType() == InventoryType.PLAYER  || e.getInventory().getType() == InventoryType.CREATIVE) && (e.getClickedInventory().getType() == InventoryType.PLAYER || e.getClickedInventory().getType() == InventoryType.CREATIVE )) {
            if(e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                String itemname = e.getCurrentItem().getItemMeta().getDisplayName();

                if(itemname.equals("§eSpeed") || itemname.equals("§eFlugmodus") || itemname.equals("§aWelten-Besuch") || itemname.equals("§ePlot abgeben") || itemname.equals("§aInventar-Settings") || itemname.equals("§cPlot bewerten")) {
                    e.setCancelled(true);
                    e.setResult(Event.Result.DENY);
                }
            }
        }


        if(e.getInventory().getName() != null && e.getCurrentItem().getItemMeta().getDisplayName() != null) {
            if(e.getInventory().getName().equals("§cContrayBuild - SpecialItems") && e.getClickedInventory().getName().equals("§cContrayBuild - SpecialItems")) {
                e.setCancelled(true);
                e.setResult(Event.Result.DENY);

                String itemname = e.getCurrentItem().getItemMeta().getDisplayName();
                int freeSlot = -1;
                int speed = -1;
                int fly = -1;
                int settings = -1;
                int visit = -1;
                int finish = -1;
                int rate = -1;

                for(int i = 0; i < 36; i++) {
                    if(p.getInventory().getItem(i) == null || p.getInventory().getItem(i).getType() == Material.AIR) freeSlot = i;
                    if(p.getInventory().getItem(i) != null && p.getInventory().getItem(i).getType() == Material.SUGAR && p.getInventory().getItem(i).getItemMeta().getDisplayName() != null && p.getInventory().getItem(i).getItemMeta().getDisplayName().equals("§eSpeed")) speed = i;
                    if(p.getInventory().getItem(i) != null && p.getInventory().getItem(i).getType() == Material.FEATHER && p.getInventory().getItem(i).getItemMeta().getDisplayName() != null && p.getInventory().getItem(i).getItemMeta().getDisplayName().equals("§eFlugmodus")) fly = i;
                    if(p.getInventory().getItem(i) != null && p.getInventory().getItem(i).getType() == Material.ENDER_PEARL && p.getInventory().getItem(i).getItemMeta().getDisplayName() != null && p.getInventory().getItem(i).getItemMeta().getDisplayName().equals("§aWelten-Besuch")) visit = i;
                    if(p.getInventory().getItem(i) != null && p.getInventory().getItem(i).getType() == Material.WORKBENCH && p.getInventory().getItem(i).getItemMeta().getDisplayName() != null && p.getInventory().getItem(i).getItemMeta().getDisplayName().equals("§aInventar-Settings")) settings = i;
                    if(p.getInventory().getItem(i) != null && p.getInventory().getItem(i).getType() == Material.EMERALD && p.getInventory().getItem(i).getItemMeta().getDisplayName() != null && p.getInventory().getItem(i).getItemMeta().getDisplayName().equals("§ePlot abgeben")) finish = i;
                    if(p.getInventory().getItem(i) != null && p.getInventory().getItem(i).getType() == Material.PAPER && p.getInventory().getItem(i).getItemMeta().getDisplayName() != null && p.getInventory().getItem(i).getItemMeta().getDisplayName().equals("§cPlot bewerten")) rate = i;
                }

                if(itemname.equals("§eSpeed")) {
                    if(s.isInv_speed()) {
                        p.getInventory().setItem(speed, null);
                        s.setInv_speed(false);
                        INV_BuildUtils.openInventorySettings(p);
                        return;
                    } else {
                        if (freeSlot != -1) {
                            p.getInventory().setItem(freeSlot, e.getCurrentItem());
                            s.setInv_speed(true);
                            INV_BuildUtils.openInventorySettings(p);
                            return;
                        } else {
                            p.closeInventory();
                            Messager.toPlayer(MessagerType.COLORED, Message.specialitem_noFreeSlot, p);
                            return;
                        }
                    }
                }

                if(itemname.equals("§eFlugmodus")) {
                    if(s.isInv_fly()) {
                        p.getInventory().setItem(fly, null);
                        s.setInv_fly(false);
                        INV_BuildUtils.openInventorySettings(p);
                        return;
                    } else {
                        if (freeSlot != -1) {
                            p.getInventory().setItem(freeSlot, e.getCurrentItem());
                            s.setInv_fly(true);
                            INV_BuildUtils.openInventorySettings(p);
                            return;
                        } else {
                            p.closeInventory();
                            Messager.toPlayer(MessagerType.COLORED, Message.specialitem_noFreeSlot, p);
                            return;
                        }
                    }
                }

                if(itemname.equals("§aWelten-Besuch")) {
                    if(s.isInv_teleport()) {
                        p.getInventory().setItem(visit, null);
                        s.setInv_teleport(false);
                        INV_BuildUtils.openInventorySettings(p);
                        return;
                    } else {
                        if (freeSlot != -1) {
                            p.getInventory().setItem(freeSlot, e.getCurrentItem());
                            s.setInv_teleport(true);
                            INV_BuildUtils.openInventorySettings(p);
                            return;
                        } else {
                            p.closeInventory();
                            Messager.toPlayer(MessagerType.COLORED, Message.specialitem_noFreeSlot, p);
                            return;
                        }
                    }
                }

                if(itemname.equals("§aInventar-Settings")) {
                    if(s.isInv_invsettings()) {
                        p.getInventory().setItem(settings, null);
                        s.setInv_invsettings(false);
                        INV_BuildUtils.openInventorySettings(p);
                        return;
                    } else {
                        if (freeSlot != -1) {
                            p.getInventory().setItem(freeSlot, e.getCurrentItem());
                            s.setInv_invsettings(true);
                            INV_BuildUtils.openInventorySettings(p);
                            return;
                        } else {
                            p.closeInventory();
                            Messager.toPlayer(MessagerType.COLORED, Message.specialitem_noFreeSlot, p);
                            return;
                        }
                    }
                }

                if(itemname.equals("§ePlot abgeben")) {
                    if(s.isInv_finish()) {
                        p.getInventory().setItem(finish, null);
                        s.setInv_finish(false);
                        INV_BuildUtils.openInventorySettings(p);
                        return;
                    } else {
                        if (freeSlot != -1) {
                            p.getInventory().setItem(freeSlot, e.getCurrentItem());
                            s.setInv_finish(true);
                            INV_BuildUtils.openInventorySettings(p);
                            return;
                        } else {
                            p.closeInventory();
                            Messager.toPlayer(MessagerType.COLORED, Message.specialitem_noFreeSlot, p);
                            return;
                        }
                    }
                }

                if(itemname.equals("§cPlot bewerten")) {
                    if(s.isInv_rate()) {
                        p.getInventory().setItem(rate, null);
                        s.setInv_rate(false);
                        INV_BuildUtils.openInventorySettings(p);
                        return;
                    } else {
                        if (freeSlot != -1) {
                            p.getInventory().setItem(freeSlot, e.getCurrentItem());
                            s.setInv_rate(true);
                            INV_BuildUtils.openInventorySettings(p);
                            return;
                        } else {
                            p.closeInventory();
                            Messager.toPlayer(MessagerType.COLORED, Message.specialitem_noFreeSlot, p);
                            return;
                        }
                    }
                }

                if(itemname.equals("§aReload")) {
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.changeinventory", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                        INV_BuildUtils.openInventorySettings(p);
                        return;
                    }
                }

                if(itemname.equals("§cZurück")) {
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.openinv", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                        INV_BuildUtils.openGUI(p);
                        return;
                    }
                }

            }

            if(e.getInventory().getName().equals("§cContrayBuild - SpecialItems") && e.getClickedInventory().getType() == InventoryType.PLAYER) {
                if(e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                    String itemname = e.getCurrentItem().getItemMeta().getDisplayName();

                    if(itemname.equals("§eSpeed") || itemname.equals("§eFlugmodus") || itemname.equals("§aWelten-Besuch") || itemname.equals("§ePlot abgeben") || itemname.equals("§aInventar-Settings") || itemname.equals("§cPlot bewerten")) {
                        if(e.getClick() != ClickType.LEFT) {
                            e.setCancelled(true);
                            e.setResult(Event.Result.DENY);
                            return;
                        }
                    }
                }
            }


        }


    }

    @EventHandler
    public void onClickItem(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Spieler s = SpielerManager.getSpieler(p);

        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if(p.getItemInHand() != null && p.getItemInHand().getItemMeta().getDisplayName() != null) {
                String itemname = p.getItemInHand().getItemMeta().getDisplayName();
                if (itemname.equals("§eSpeed") || itemname.equals("§eFlugmodus") || itemname.equals("§aWelten-Besuch") || itemname.equals("§ePlot abgeben") || itemname.equals("§aInventar-Settings") || itemname.equals("§cPlot bewerten")) {
                    e.setCancelled(true);

                    if(itemname.equals("§eSpeed")) {
                        if(!s.isSpeed()) {
                            p.setFlySpeed(((float) 0.5));
                            p.setWalkSpeed(((float) 0.5));
                            s.setSpeed(true);
                            Messager.toPlayer(MessagerType.COLORED, Message.specialitem_speedFast, p);
                            return;
                        } else {
                            p.setFlySpeed(((float) 0.2));
                            p.setWalkSpeed(((float) 0.2));
                            s.setSpeed(false);
                            Messager.toPlayer(MessagerType.COLORED, Message.specialitem_speedSlow, p);
                            return;
                        }
                    }

                    if(itemname.equals("§eFlugmodus")) {
                        if(p.getAllowFlight()) {
                            p.setAllowFlight(false);
                            return;
                        } else {
                            p.setAllowFlight(true);
                            return;
                        }
                    }

                    if(itemname.equals("§aWelten-Besuch")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.worldmanagement.visit", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.worldmanagement.*"), true, true))) {
                            INV_BuildUtils.openWorldVisit(p, 1);
                            return;
                        }
                    }

                    if(itemname.equals("§ePlot abgeben")) {
                        if(PermissionManager.check(p, new CustomPerm("contray.buildutils.plotfinish", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                            INV_BuildUtils.openPlotFinishing(p);
                            return;
                        }
                    }

                    if(itemname.equals("§aInventar-Settings")) {
                        if(PermissionManager.check(p, new CustomPerm("contray.buildutils.changeinventory", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                            INV_BuildUtils.openInventorySettings(p);
                            return;
                        }
                    }

                    if(itemname.equals("§cPlot bewerten")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.plotrate", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openPlotManagement(p, 1);
                            return;
                        }
                    }
                }
            }
        }

    }

}
