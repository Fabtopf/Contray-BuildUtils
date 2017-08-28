package de.Fabtopf.BuildUtils.Listener;

import de.Fabtopf.BuildUtils.API.*;
import de.Fabtopf.BuildUtils.API.Enum.MessagerType;
import de.Fabtopf.BuildUtils.API.Manager.ItemManager;
import de.Fabtopf.BuildUtils.API.Manager.ModuleManager;
import de.Fabtopf.BuildUtils.API.Manager.PermissionManager;
import de.Fabtopf.BuildUtils.Utilities.Cache.Settings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Created by Fabi on 20.08.2017.
 */
public class ITEMPROTECTION_Protection implements Listener {

    private Module module = ModuleManager.getModule("ItemProtection");

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();
            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.itemprotect.exempt", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.itemprotect.*"), false, true))) {
                return;
            } else {

                if(e.getItem().getItemMeta().getDisplayName() != null) {
                    String itemname = e.getItem().getItemMeta().getDisplayName();
                    if (itemname.equals("§eSpeed") || itemname.equals("§eFlugmodus") || itemname.equals("§aWelten-Besuch") || itemname.equals("§ePlot abgeben") || itemname.equals("§aInventar-Settings") || itemname.equals("§cPlot bewerten"))
                        return;
                }

                int id = p.getItemInHand().getTypeId();
                int id2 = e.getClickedBlock().getTypeId();

                if (ItemManager.getItem(id) != null) {
                    if (!ItemManager.getItem(id).isInteract()) {
                        Messager.toPlayer(MessagerType.COLORED, Message.itemprotection_notAllowed, p);
                        e.setCancelled(true);
                    }
                }

                if(ItemManager.getItem(id2) != null) {
                    if (!ItemManager.getItem(id2).isInteract()) {
                        Messager.toPlayer(MessagerType.COLORED, Message.itemprotection_notAllowed, p);
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = (Player) e.getWhoClicked();
            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.itemprotect.exempt", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.itemprotect.*"), false, true))) {
                return;
            } else {
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
                    return;
                } else {

                    if((e.getClickedInventory().getType() == InventoryType.PLAYER  || e.getClickedInventory().getType() == InventoryType.CREATIVE || e.getClickedInventory().getType() == InventoryType.CRAFTING) && e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                        String itemname = e.getCurrentItem().getItemMeta().getDisplayName();
                        if (itemname.equals("§eSpeed") || itemname.equals("§eFlugmodus") || itemname.equals("§aWelten-Besuch") || itemname.equals("§ePlot abgeben") || itemname.equals("§aInventar-Settings") || itemname.equals("§cPlot bewerten"))
                            return;
                    }

                    int id = e.getCurrentItem().getTypeId();

                    if (ItemManager.getItem(id) != null) {
                        if (!ItemManager.getItem(id).isInventory()) {
                            e.setCancelled(true);
                            e.setResult(Event.Result.DENY);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryItemDrag(InventoryDragEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = (Player) e.getWhoClicked();
            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.itemprotect.exempt", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.itemprotect.*"), false, true))) {
                return;
            } else {
                if (e.getCursor() == null || e.getCursor().getType() == Material.AIR) {
                    return;
                } else {

                    if((e.getInventory().getType() == InventoryType.PLAYER  || e.getInventory().getType() == InventoryType.CREATIVE || e.getInventory().getType() == InventoryType.CRAFTING) && e.getCursor().getItemMeta().getDisplayName() != null) {
                        String itemname = e.getCursor().getItemMeta().getDisplayName();
                        if (itemname.equals("§eSpeed") || itemname.equals("§eFlugmodus") || itemname.equals("§aWelten-Besuch") || itemname.equals("§ePlot abgeben") || itemname.equals("§aInventar-Settings") || itemname.equals("§cPlot bewerten"))
                            return;
                    }

                    int id = e.getCursor().getTypeId();
                    int id2 = e.getOldCursor().getTypeId();

                    if (ItemManager.getItem(id) != null) {
                        if (!ItemManager.getItem(id).isInventory()) {
                            e.setCancelled(true);
                            e.setResult(Event.Result.DENY);
                        }
                    }

                    if (ItemManager.getItem(id2) != null) {
                        if (!ItemManager.getItem(id2).isInventory()) {
                            e.setCancelled(true);
                            e.setResult(Event.Result.DENY);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();
            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.itemprotect.exempt", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.itemprotect.*"), false, true))) {
                return;
            } else {
                int id = e.getItem().getItemStack().getTypeId();

                if (ItemManager.getItem(id) != null) {
                    if (!ItemManager.getItem(id).isInventory()) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();
            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.itemprotect.exempt", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.itemprotect.*"), false, true))) {
                return;
            } else {
                int id = p.getItemInHand().getTypeId();

                if (ItemManager.getItem(id) != null) {
                    if (!ItemManager.getItem(id).isPlace()) {
                        Messager.toPlayer(MessagerType.COLORED, Message.itemprotection_notAllowed, p);
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEmptyBucket(PlayerBucketEmptyEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();
            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.itemprotect.exempt", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.itemprotect.*"), false, true))) {
                return;
            } else {
                int id = p.getItemInHand().getTypeId();

                if (ItemManager.getItem(id) != null) {
                    if (!ItemManager.getItem(id).isEmptyBucket()) {
                        Messager.toPlayer(MessagerType.COLORED, Message.itemprotection_notAllowed, p);
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onFillBucket(PlayerBucketFillEvent e) {
        if(module.isEnabled() && (!module.isDevmode() || (module.isDevmode() && Settings.devmode))) {
            Player p = e.getPlayer();
            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.itemprotect.exempt", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.itemprotect.*"), false, true))) {
                return;
            } else {
                int id = p.getItemInHand().getTypeId();

                if (ItemManager.getItem(id) != null) {
                    if (!ItemManager.getItem(id).isFillBucket()) {
                        Messager.toPlayer(MessagerType.COLORED, Message.itemprotection_notAllowed, p);
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

}
