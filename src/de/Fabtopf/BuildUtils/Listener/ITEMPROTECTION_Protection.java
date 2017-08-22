package de.Fabtopf.BuildUtils.Listener;

import de.Fabtopf.BuildUtils.API.*;
import de.Fabtopf.BuildUtils.API.Enum.MessagerType;
import de.Fabtopf.BuildUtils.API.Manager.ItemManager;
import de.Fabtopf.BuildUtils.API.Manager.ModuleManager;
import de.Fabtopf.BuildUtils.API.Manager.PermissionManager;
import de.Fabtopf.BuildUtils.Utilities.Cache.Settings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
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
                int id = p.getItemInHand().getTypeId();

                if (ItemManager.getItem(id) != null) {
                    if (!ItemManager.getItem(id).isInteract()) {
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

                    int id = e.getCurrentItem().getTypeId();

                    if (ItemManager.getItem(id) != null) {
                        if (!ItemManager.getItem(id).isInventory()) {
                            e.setCancelled(true);
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
