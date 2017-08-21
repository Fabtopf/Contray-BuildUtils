package de.Fabtopf.BuildUtils.Listener;

import de.Fabtopf.BuildUtils.API.Converter;
import de.Fabtopf.BuildUtils.API.CustomPerm;
import de.Fabtopf.BuildUtils.API.Manager.ModuleManager;
import de.Fabtopf.BuildUtils.API.Manager.PermissionManager;
import de.Fabtopf.BuildUtils.API.Module;
import de.Fabtopf.BuildUtils.Commands.Inventory.INV_BuildUtils;
import de.Fabtopf.BuildUtils.Utilities.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Fabi on 20.08.2017.
 */
public class SERVER_InventoryInteract implements Listener {

    public SERVER_InventoryInteract() {
        Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onInventoryInteract(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        String invname = null;
        String itemname = null;
        ItemMeta itemMeta = null;

        if(e.getInventory().getName() != null) invname = e.getInventory().getName();
        if(e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName() != null) itemname = e.getCurrentItem().getItemMeta().getDisplayName();
        if(e.getCurrentItem().getItemMeta() != null) itemMeta = e.getCurrentItem().getItemMeta();

        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getInventory().getType() == InventoryType.PLAYER) {
            return;
        }

        if(invname != null && itemname != null) {

            /*
             *  ContrayBuild - Management
             */

            if(invname.equals("§cContrayBuild - Management")) {
                e.setCancelled(true);

                if(itemname.equals("§e§lWorld-Management")) {
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                        INV_BuildUtils.openWorldManagement(p, 1);
                        return;
                    }
                }

                if(itemname.equals("§a§lSpieler-Management")) {
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.player", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                        INV_BuildUtils.openPlayerManagement(p);
                        return;
                    }
                }

                if(itemname.equals("§6§lModule-Settings")) {
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.module", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                        INV_BuildUtils.openModuleManagement(p, 1);
                        return;
                    }
                }

                if(itemname.equals("§c§lItem-Management")) {
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.item", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                        INV_BuildUtils.openItemManagement(p, 1);
                        return;
                    }
                }

                if(itemname.equals("§9§lFertige Plots")) {
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.plotrate", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                        INV_BuildUtils.openPlotManagement(p);
                        return;
                    }
                }
            }

            /*
             *  ContrayBuild - Modules
             */

            if(invname.equals("§cContrayBuild - Modules")) {
                e.setCancelled(true);

                if(itemname.equals("§aReload")) {
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.module", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                        INV_BuildUtils.openModuleManagement(p, 1);
                        return;
                    }
                }

                if(itemname.equals("§cZurück")) {
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.openinv", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                        INV_BuildUtils.openGUI(p);
                        return;
                    }
                }

                if(itemname.contains("§eZu Seite ")) {
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.module", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {

                        int page = 1;

                        try {
                            page = Integer.valueOf(itemname.replace("§eZu Seite ", ""));
                        } catch(Exception exc) {

                        }

                        INV_BuildUtils.openModuleManagement(p, page);
                        return;
                    }
                }

                if(ModuleManager.getModule(ChatColor.stripColor(itemname)) != null) {
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.module", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                        Module mod = ModuleManager.getModule(ChatColor.stripColor(itemname));
                        if (mod.isEnabled()) {
                            mod.setEnabled(false);
                        } else {
                            mod.setEnabled(true);
                        }

                        INV_BuildUtils.openModuleManagement(p, 1);
                        return;
                    }
                }

            }

            /*
             *  ContrayBuild - Items
             */

            if(invname.equals("§cContrayBuild - Items")) {
                e.setCancelled(true);

                if(itemname.equals("§aReload")) {
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                        INV_BuildUtils.openItemManagement(p, 1);
                        return;
                    }
                }

                if(itemname.equals("§cZurück")) {
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.openinv", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                        INV_BuildUtils.openGUI(p);
                        return;
                    }
                }

                if(itemname.equals("§9Liste editieren")) {
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                        INV_BuildUtils.openItemManagementEdit(p);
                        return;
                    }
                }

                if(itemname.contains("§eZu Seite ")) {
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {

                        int page = 1;

                        try {
                            page = Integer.valueOf(itemname.replace("§eZu Seite ", ""));
                        } catch(Exception exc) {

                        }

                        INV_BuildUtils.openItemManagement(p, page);
                        return;
                    }
                }

            }

        }
    }

}
