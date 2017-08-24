package de.Fabtopf.BuildUtils.Listener;

import com.intellectualcrafters.plot.api.PlotAPI;
import de.Fabtopf.BuildUtils.API.*;
import de.Fabtopf.BuildUtils.API.Enum.MessagerType;
import de.Fabtopf.BuildUtils.API.Enum.PlotState;
import de.Fabtopf.BuildUtils.API.Manager.*;
import de.Fabtopf.BuildUtils.Commands.Inventory.INV_BuildUtils;
import de.Fabtopf.BuildUtils.Utilities.Main;
import de.Fabtopf.BuildUtils.Utilities.MySQL.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Location;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

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
        if(e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName() != null) itemname = e.getCurrentItem().getItemMeta().getDisplayName();
        if(e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null) itemMeta = e.getCurrentItem().getItemMeta();

        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getInventory().getType() == InventoryType.PLAYER) {
            return;
        } else if(e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName() && e.getCurrentItem().getItemMeta().getDisplayName().equals("§0")) {
            e.setCancelled(true);
            return;
        } else {

            if (invname != null && itemname != null) {

            /*
             *  ContrayBuild - Management
             */

                if (invname.equals("§cContrayBuild - Management")) {
                    e.setCancelled(true);

                    if (itemname.equals("§e§lWorld-Management")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagement(p, 1);
                            return;
                        }
                    }

                    if (itemname.equals("§a§lSpieler-Management")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.player", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openPlayerManagement(p, 1);
                            return;
                        }
                    }

                    if (itemname.equals("§c§lPlugin-Settings")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.settings", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openPluginSettings(p);
                            return;
                        }
                    }

                    if (itemname.equals("§6§lModule-Settings")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.module", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openModuleManagement(p, 1);
                            return;
                        }
                    }

                    if (itemname.equals("§5§lWelten besuchen")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.worldmanagement.visit", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.worldmanagement.*"), true, true))) {
                            INV_BuildUtils.openWorldVisit(p, 1);
                            return;
                        }
                    }

                    if (itemname.equals("§c§lItem-Management")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.item", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openItemManagement(p, 1);
                            return;
                        }
                    }

                    if (itemname.equals("§9§lFertige Plots")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.plotrate", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openPlotManagement(p, 1);
                            return;
                        }
                    }

                    if(itemname.equals("§9§lPlot abgeben")) {
                        if(PermissionManager.check(p, new CustomPerm("contray.buildutils.plotfinish", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                            INV_BuildUtils.openPlotFinishing(p);
                            return;
                        }
                    }

                    if(itemname.equals("§e§lInventar-Einstellungen")) {
                        if(PermissionManager.check(p, new CustomPerm("contray.buildutils.changeinventory", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                            INV_BuildUtils.openInventorySettings(p);
                            return;
                        }
                    }
                }

            /*
             *  ContrayBuild - Modules
             */

                if (invname.equals("§cContrayBuild - Modules")) {
                    e.setCancelled(true);

                    if (itemname.equals("§aReload")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.module", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openModuleManagement(p, 1);
                            return;
                        }
                    }

                    if (itemname.equals("§cZurück")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.openinv", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                            INV_BuildUtils.openGUI(p);
                            return;
                        }
                    }

                    if (itemname.contains("§eZu Seite ")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.module", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {

                            int page = 1;

                            try {
                                page = Integer.valueOf(itemname.replace("§eZu Seite ", ""));
                            } catch (Exception exc) {

                            }

                            INV_BuildUtils.openModuleManagement(p, page);
                            return;
                        }
                    }

                    if (ModuleManager.getModule(ChatColor.stripColor(itemname)) != null) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.module", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
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

                if (invname.equals("§cContrayBuild - Items")) {
                    e.setCancelled(true);

                    if (itemname.equals("§aReload")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openItemManagement(p, 1);
                            return;
                        }
                    }

                    if (itemname.equals("§cZurück")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.openinv", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                            INV_BuildUtils.openGUI(p);
                            return;
                        }
                    }

                    if (itemname.equals("§9Liste editieren")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openItemManagementEdit(p, null);
                            return;
                        }
                    }

                    if (itemname.contains("§eZu Seite ")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {

                            int page = 1;

                            try {
                                page = Integer.valueOf(itemname.replace("§eZu Seite ", ""));
                            } catch (Exception exc) {

                            }

                            INV_BuildUtils.openItemManagement(p, page);
                            return;
                        }
                    }

                    if(itemname.equals("§e" + e.getCurrentItem().getType().name())) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            if(e.getClick() == ClickType.LEFT) {
                                ItemManager.unregisterItem(e.getCurrentItem().getTypeId());
                                INV_BuildUtils.openItemManagement(p, 1);
                                return;
                            } else if(e.getClick() == ClickType.MIDDLE) {
                                Item item = ItemManager.getItem(e.getCurrentItem().getTypeId());
                                if(item != null) {
                                    INV_BuildUtils.openItemManagementSettings(p, item);
                                    return;
                                } else {
                                    INV_BuildUtils.openItemManagement(p, 1);
                                    return;
                                }
                            }
                        }
                    }

                }

                /*
                 *  ContrayBuild - ItemSettings
                 */

                if(invname.equals("§cContrayBuild - ItemSettings")) {
                    e.setCancelled(true);

                    Item item = ItemManager.getItem(e.getInventory().getItem(13).getTypeId());
                    if(item == null) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openItemManagement(p, 1);
                        }
                        return;
                    }

                    if(itemname.equals("§aReload")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openItemManagementSettings(p, item);
                            return;
                        }
                    }

                    if(itemname.equals("§cZurück")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openItemManagement(p, 1);
                            return;
                        }
                    }

                    if(itemname.equals("§aEimer befüllen") || itemname.equals("§cEimer befüllen")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            if(item.isFillBucket()) {
                                item.setFillBucket(false);
                                INV_BuildUtils.openItemManagementSettings(p, item);
                                return;
                            } else {
                                item.setFillBucket(true);
                                INV_BuildUtils.openItemManagementSettings(p, item);
                                return;
                            }
                        }
                    }

                    if(itemname.equals("§aEimer entleeren") || itemname.equals("§cEimer entleeren")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            if(item.isEmptyBucket()) {
                                item.setEmptyBucket(false);
                                INV_BuildUtils.openItemManagementSettings(p, item);
                                return;
                            } else {
                                item.setEmptyBucket(true);
                                INV_BuildUtils.openItemManagementSettings(p, item);
                                return;
                            }
                        }
                    }

                    if(itemname.equals("§aInteraktion") || itemname.equals("§cInteraktion")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            if(item.isInteract()) {
                                item.setInteract(false);
                                INV_BuildUtils.openItemManagementSettings(p, item);
                                return;
                            } else {
                                item.setInteract(true);
                                INV_BuildUtils.openItemManagementSettings(p, item);
                                return;
                            }
                        }
                    }

                    if(itemname.equals("§aInventar") || itemname.equals("§cInventar")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            if(item.isInventory()) {
                                item.setInventory(false);
                                INV_BuildUtils.openItemManagementSettings(p, item);
                                return;
                            } else {
                                item.setInventory(true);
                                INV_BuildUtils.openItemManagementSettings(p, item);
                                return;
                            }
                        }
                    }

                    if(itemname.equals("§aPlatzieren") || itemname.equals("§cPlatzieren")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            if(item.isPlace()) {
                                item.setPlace(false);
                                INV_BuildUtils.openItemManagementSettings(p, item);
                                return;
                            } else {
                                item.setPlace(true);
                                INV_BuildUtils.openItemManagementSettings(p, item);
                                return;
                            }
                        }
                    }

                }

                /*
                 *  ContrayBuild - Welten
                 */

                if(invname.equals("§cContrayBuild - Welten")) {
                    e.setCancelled(true);

                    if(itemname.equals("§aReload")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagement(p, 1);
                            return;
                        }
                    }

                    if(itemname.equals("§cZurück")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.openinv", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                            INV_BuildUtils.openGUI(p);
                            return;
                        }
                    }

                    if(WeltenManager.getWelt(ChatColor.stripColor(itemname)) != null) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagementSettings(p, WeltenManager.getWelt(ChatColor.stripColor(itemname)));
                            return;
                        }
                    } else {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagement(p, 1);
                            return;
                        }
                    }
                }

                /*
                 *  ContrayBuild - WeltenSettings
                 */

                if(invname.equals("§cContrayBuild - WeltenSettings")) {
                    e.setCancelled(true);

                    Welt welt = WeltenManager.getWelt(ChatColor.stripColor(e.getInventory().getItem(13).getItemMeta().getDisplayName()));
                    if(welt == null) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagement(p, 1);
                        }
                        return;
                    }

                    if(itemname.equals("§aReload")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagementSettings(p, welt);
                            return;
                        }
                    }

                    if(itemname.equals("§cZurück")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagement(p, 1);
                            return;
                        }
                    }

                    if(itemname.equals("§aManaged") || itemname.equals("§cManaged")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            if(welt.isManaged()) {
                                welt.setManaged(false);
                                INV_BuildUtils.openWorldManagementSettings(p, welt);
                                return;
                            } else {
                                welt.setManaged(true);
                                INV_BuildUtils.openWorldManagementSettings(p, welt);
                                return;
                            }
                        }
                    }

                    if(itemname.equals("§aLobby") || itemname.equals("§cLobby")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            if(welt.isLobby()) {
                                welt.setLobby(false);
                                INV_BuildUtils.openWorldManagementSettings(p, welt);
                                return;
                            } else {
                                if(welt.isOpen()) welt.setOpen(false);
                                welt.setLobby(true);
                                INV_BuildUtils.openWorldManagementSettings(p, welt);
                                return;
                            }
                        }
                    }

                    if(itemname.equals("§aÖffentlich") || itemname.equals("§cÖffentlich")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            if(e.getClick() == ClickType.LEFT) {
                                if (welt.isOpen()) {
                                    welt.setOpen(false);
                                    INV_BuildUtils.openWorldManagementSettings(p, welt);
                                    return;
                                } else {
                                    if (welt.isLobby()) welt.setLobby(false);
                                    welt.setOpen(true);
                                    INV_BuildUtils.openWorldManagementSettings(p, welt);
                                    return;
                                }
                            } else if(e.getClick() == ClickType.MIDDLE) {
                                INV_BuildUtils.openWorldManagementGradeSettings(p, welt);
                                return;
                            }
                        }
                    }

                    if(itemname.equals("§eBuilders")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagementBuilders(p, welt, 1);
                            return;
                        }
                    }

                    if(itemname.equals("§3Besucher")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagementCustomers(p, welt, 1);
                            return;
                        }
                    }

                }

                /*
                 *  ContrayBuild - WeltStufe
                 */

                if(invname.equals("§cContrayBuild - WeltStufe")) {
                    e.setCancelled(true);

                    Welt welt = WeltenManager.getWelt(ChatColor.stripColor(e.getInventory().getItem(13).getItemMeta().getDisplayName()));
                    if(welt == null) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagement(p, 1);
                        }
                        return;
                    }

                    if(itemname.equals("§aReload")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagementGradeSettings(p, welt);
                            return;
                        }
                    }

                    if(itemname.equals("§cZurück")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagementSettings(p, welt);
                            return;
                        }
                    }

                    if(itemname.equals("§cStufe erhöhen")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            welt.setNeededGrade(welt.getNeededGrade() + 1);
                            INV_BuildUtils.openWorldManagementGradeSettings(p, welt);
                            return;
                        }
                    }

                    if(itemname.equals("§cStufe senken")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            welt.setNeededGrade(welt.getNeededGrade() - 1);
                            INV_BuildUtils.openWorldManagementGradeSettings(p, welt);
                            return;
                        }
                    }

                }

                /*
                 *  ContrayBuild - SpielerSettings
                 */

                if(invname.equals("§cContrayBuild - SpielerSettings")) {
                    e.setCancelled(true);

                    if(itemname.equals("§aReload")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.player", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openPlayerManagement(p, 1);
                            return;
                        }
                    }

                    if(itemname.equals("§cZurück")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.openinv", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                            INV_BuildUtils.openGUI(p);
                            return;
                        }
                    }

                    if(itemname.contains("§eZu Seite ")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.player", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {

                            int page = 1;

                            try {
                                page = Integer.valueOf(itemname.replace("§eZu Seite ", ""));
                            } catch (Exception exc) {

                            }

                            INV_BuildUtils.openPlayerManagement(p, page);
                            return;
                        }
                    }

                    if(itemname.contains("§9Offline Spieler")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.player", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openPlayerManagementEdit(p);
                            return;
                        }
                    }

                    if(Bukkit.getPlayer(ChatColor.stripColor(itemname)) != null && SpielerManager.getSpieler(Bukkit.getPlayer(ChatColor.stripColor(itemname))) != null) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.player", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            Spieler s = SpielerManager.getSpieler(Bukkit.getPlayer(ChatColor.stripColor(itemname)));

                            INV_BuildUtils.openPlayerManagementSettings(p, s);
                            return;
                        }
                    }

                }

                /*
                 *  ContrayBuild - SpielerStufe
                 */

                if(invname.equals("§cContrayBuild - SpielerStufe")) {
                    e.setCancelled(true);


                    Spieler s = null;
                    OfflinePlayer op = Bukkit.getOfflinePlayer(ChatColor.stripColor(e.getInventory().getItem(13).getItemMeta().getDisplayName()));
                    if(op.isOnline()) s = SpielerManager.getSpieler(Bukkit.getPlayer(op.getUniqueId()));

                    if(itemname.equals("§aReload")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.player", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            if(s != null) {
                                INV_BuildUtils.openPlayerManagementSettings(p, s);
                            } else {
                                INV_BuildUtils.openPlayerManagementSettings(p, op);
                            }
                            return;
                        }
                    }

                    if(itemname.equals("§cZurück")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.player", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openPlayerManagement(p, 1);
                            return;
                        }
                    }

                    if(itemname.equals("§cStufe erhöhen")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.player", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            if(s != null) {
                                s.setBuilderGrade(s.getBuilderGrade() + 1);
                                INV_BuildUtils.openPlayerManagementSettings(p, s);
                            } else {
                                int id = Utils.getPlayerID(op);
                                Utils.updateBuilderGrade(id, Utils.getBuilderGrade(id) + 1);
                                INV_BuildUtils.openPlayerManagementSettings(p, op);
                            }
                            return;
                        }
                    }

                    if(itemname.equals("§cStufe senken")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.player", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            if(s != null) {
                                s.setBuilderGrade(s.getBuilderGrade() - 1);
                                INV_BuildUtils.openPlayerManagementSettings(p, s);
                            } else {
                                int id = Utils.getPlayerID(op);
                                Utils.updateBuilderGrade(id, Utils.getBuilderGrade(id) - 1);
                                INV_BuildUtils.openPlayerManagementSettings(p, op);
                            }
                            return;
                        }
                    }

                }

                /*
                 *  ContrayBuild - WeltenBesuch
                 */

                if(invname.equals("§cContrayBuild - WeltenBesuch")) {
                    e.setCancelled(true);

                    if(itemname.equals("§aReload")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.weltenmanagement.visit", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.weltenmanagement.*"), true, true))) {
                            INV_BuildUtils.openWorldVisit(p, 1);
                            return;
                        }
                    }

                    if(itemname.equals("§cZurück")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.openinv", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                            INV_BuildUtils.openGUI(p);
                            return;
                        }
                    }

                    if(itemname.contains("§eZu Seite ")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.weltenmanagement.visit", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.weltenmanagement.*"), true, true))) {

                            int page = 1;

                            try {
                                page = Integer.valueOf(itemname.replace("§eZu Seite ", ""));
                            } catch (Exception exc) {

                            }

                            INV_BuildUtils.openWorldVisit(p, page);
                            return;
                        }
                    }

                    if(WeltenManager.getWelt(ChatColor.stripColor(itemname)) != null) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.weltenmanagement.visit", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.weltenmanagement.*"), true, true))) {
                            Welt welt = WeltenManager.getWelt(ChatColor.stripColor(itemname));

                            if(welt != null && Bukkit.getWorld(welt.getName()) != null) {

                                p.closeInventory();
                                p.teleport(welt.getWorld().getSpawnLocation());
                                Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_successfullyTeleportet(welt.getName()), p);

                            } else {

                                INV_BuildUtils.openWorldVisit(p, 1);
                                Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_teleportFailed, p);

                            }

                            return;

                        }
                    }
                }

                /*
                 *  ContrayBuild - WeltenBuilders
                 */

                if(invname.equals("§cContrayBuild - WeltenBuilders")) {
                    e.setCancelled(true);

                    Welt welt = WeltenManager.getWelt(ChatColor.stripColor(e.getInventory().getItem(e.getInventory().getSize() - 8).getItemMeta().getDisplayName()));

                    if(welt == null || Bukkit.getWorld(welt.getName()) == null) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagement(p, 1);
                            return;
                        }
                    }

                    if(itemname.equals("§aReload")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagementBuilders(p, welt, 1);
                            return;
                        }
                    }

                    if(itemname.equals("§cZurück")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagementSettings(p, welt);
                            return;
                        }
                    }

                    if(itemname.contains("§eZu Seite ")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {

                            int page = 1;

                            try {
                                page = Integer.valueOf(itemname.replace("§eZu Seite ", ""));
                            } catch (Exception exc) {

                            }

                            INV_BuildUtils.openWorldManagementBuilders(p, welt, page);
                            return;
                        }
                    }

                    if(itemname.equals("§9Liste editieren")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagementBuildersEdit(p, welt);
                            return;
                        }
                    }

                    if(itemname.contains("§a")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            OfflinePlayer op = Bukkit.getOfflinePlayer(ChatColor.stripColor(itemname));

                            if(op != null) {
                                List<OfflinePlayer> builders = welt.getBuilders();
                                if(builders.contains(op)) {
                                    builders.remove(op);
                                    welt.setBuilders(builders);

                                    INV_BuildUtils.openWorldManagementBuilders(p, welt, 1);
                                }
                            }
                            return;

                        }
                    }

                }

                /*
                 *  ContrayBuild - WeltenBesucher
                 */

                if(invname.equals("§cContrayBuild - WeltenBesucher")) {
                    e.setCancelled(true);

                    Welt welt = WeltenManager.getWelt(ChatColor.stripColor(e.getInventory().getItem(e.getInventory().getSize() - 8).getItemMeta().getDisplayName()));

                    if(welt == null || Bukkit.getWorld(welt.getName()) == null) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagement(p, 1);
                            return;
                        }
                    }

                    if(itemname.equals("§aReload")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagementCustomers(p, welt, 1);
                            return;
                        }
                    }

                    if(itemname.equals("§cZurück")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagementSettings(p, welt);
                            return;
                        }
                    }

                    if(itemname.contains("§eZu Seite ")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {

                            int page = 1;

                            try {
                                page = Integer.valueOf(itemname.replace("§eZu Seite ", ""));
                            } catch (Exception exc) {

                            }

                            INV_BuildUtils.openWorldManagementCustomers(p, welt, page);
                            return;
                        }
                    }

                    if(itemname.equals("§9Liste editieren")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openWorldManagementCustomersEdit(p, welt);
                            return;
                        }
                    }

                    if(itemname.contains("§a")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            OfflinePlayer op = Bukkit.getOfflinePlayer(ChatColor.stripColor(itemname));

                            if(op != null) {
                                List<OfflinePlayer> customers = welt.getCustomers();
                                if(customers.contains(op)) {
                                    customers.remove(op);
                                    welt.setCustomers(customers);

                                    INV_BuildUtils.openWorldManagementCustomers(p, welt, 1);
                                }
                            }
                            return;

                        }
                    }

                }

                /*
                 *  ContrayBuild - PlotAbgabe
                 */

                if(invname.equals("§cContrayBuild - PlotAbgabe")) {
                    e.setCancelled(true);

                    if(itemname.equals("§cAbbrechen")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.openinv", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                            INV_BuildUtils.openGUI(p);
                            return;
                        }
                    }

                    if(itemname.equals("§aBestätigen")) {
                        if(PermissionManager.check(p, new CustomPerm("contray.buildutils.plotfinish", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                            if(PlotManager.getPlot(Bukkit.getOfflinePlayer(p.getUniqueId())) == null) {
                                PlotAPI plot = new PlotAPI();
                                if (plot.isInPlot(p)) {
                                    if (plot.getPlot(p).getOwners().contains(p.getUniqueId())) {
                                        Location side = new Location(Bukkit.getWorld(plot.getPlot(p).getSide().getWorld()), plot.getPlot(p).getSide().getX(), plot.getPlot(p).getSide().getY(), plot.getPlot(p).getSide().getZ(), plot.getPlot(p).getSide().getYaw(), plot.getPlot(p).getSide().getPitch());
                                        PlotManager.registerPlot(Bukkit.getOfflinePlayer(p.getUniqueId()), side);
                                        p.closeInventory();
                                        Messager.toPlayer(MessagerType.COLORED, Message.plotmanager_successfullyDone, p);
                                        return;
                                    } else {
                                        p.closeInventory();
                                        Messager.toPlayer(MessagerType.COLORED, Message.plotmanager_notOwnPlot, p);
                                        return;
                                    }
                                } else {
                                    p.closeInventory();
                                    Messager.toPlayer(MessagerType.COLORED, Message.plotmanager_notOnPlot, p);
                                    return;
                                }
                            } else {
                                PlotAPI plotm = new PlotAPI();
                                FinishedPlot plot = PlotManager.getPlot(Bukkit.getOfflinePlayer(p.getUniqueId()));
                                if (plotm.isInPlot(p)) {
                                    if (plotm.getPlot(p).getOwners().contains(p.getUniqueId())) {
                                        if (!plot.getPlotState().getRated()) {
                                            p.closeInventory();
                                            Messager.toPlayer(MessagerType.COLORED, Message.plotmanager_alreadyDone, p);
                                            return;
                                        } else {

                                            plot.setNoticed(false);
                                            plot.setSide(new Location(Bukkit.getWorld(plotm.getPlot(p).getSide().getWorld()), plotm.getPlot(p).getSide().getX(), plotm.getPlot(p).getSide().getY(), plotm.getPlot(p).getSide().getZ(), plotm.getPlot(p).getSide().getYaw(), plotm.getPlot(p).getSide().getPitch()));
                                            plot.updatePlotState(PlotState.notRated);

                                            p.closeInventory();
                                            Messager.toPlayer(MessagerType.COLORED, Message.plotmanager_successfullyDone, p);
                                            return;
                                        }
                                    } else {
                                        p.closeInventory();
                                        Messager.toPlayer(MessagerType.COLORED, Message.plotmanager_notOwnPlot, p);
                                        return;
                                    }
                                } else {
                                    p.closeInventory();
                                    Messager.toPlayer(MessagerType.COLORED, Message.plotmanager_notOnPlot, p);
                                    return;
                                }
                            }
                        }
                    }

                }

                /*
                 *  ContrayBuild - Plots
                 */

                if(invname.equals("§cContrayBuild - Plots")) {
                    e.setCancelled(true);

                    if(itemname.equals("§cZurück")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.openinv", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                            INV_BuildUtils.openGUI(p);
                            return;
                        }
                    }

                    if(itemname.equals("§aReload")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.plotrate", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                            INV_BuildUtils.openPlotManagement(p, 1);
                            return;
                        }
                    }

                    if(itemname.startsWith("§e") && Bukkit.getOfflinePlayer(ChatColor.stripColor(itemname)) != null && PlotManager.getPlot(Bukkit.getOfflinePlayer(ChatColor.stripColor(itemname))) != null) {
                        FinishedPlot plot = PlotManager.getPlot(Bukkit.getOfflinePlayer(ChatColor.stripColor(itemname)));

                        if(plot.getSide().getWorld() == null) plot.setSide(Utils.getPlotSide(plot.getID()));

                        if(Bukkit.getWorld(plot.getSide().getWorld().getName()) != null) WeltenManager.registerWelt(plot.getSide().getWorld().getName());

                        Spieler s = SpielerManager.getSpieler(p);
                        if(!s.isRate()) {
                            if (ModuleManager.getModule("WorldManagement").isEnabled()) {
                                Welt welt = null;
                                if(plot.getSide().getWorld() != null && WeltenManager.getWelt(plot.getSide().getWorld().getName()) != null) welt = WeltenManager.getWelt(plot.getSide().getWorld().getName());

                                if (welt != null && welt.isManaged()) {
                                    if ((welt.isOpen() && (s.getBuilderGrade() >= welt.getNeededGrade())) || welt.isLobby() || welt.getBuilders().contains(Bukkit.getOfflinePlayer(p.getUniqueId())) || welt.getCustomers().contains(Bukkit.getOfflinePlayer(p.getUniqueId()))) {
                                        p.closeInventory();
                                        p.teleport(plot.getSide());
                                        p.getInventory().clear();

                                        ItemStack bad = new ItemStack(Material.WOOL, 1, (byte) 14);
                                        ItemMeta badMeta = bad.getItemMeta();
                                        badMeta.setDisplayName("§cSchlecht");
                                        bad.setItemMeta(badMeta);

                                        ItemStack good = new ItemStack(Material.WOOL, 1, (byte) 1);
                                        ItemMeta goodMeta = good.getItemMeta();
                                        goodMeta.setDisplayName("§6Weiterbauen");
                                        good.setItemMeta(goodMeta);

                                        ItemStack accepted = new ItemStack(Material.WOOL, 1, (byte) 5);
                                        ItemMeta acceptedMeta = accepted.getItemMeta();
                                        acceptedMeta.setDisplayName("§aAngenommen");
                                        accepted.setItemMeta(acceptedMeta);

                                        ItemStack cancel = new ItemStack(Material.BARRIER);
                                        ItemMeta cancelMeta = cancel.getItemMeta();
                                        cancelMeta.setDisplayName("§cAbbrechen");
                                        cancel.setItemMeta(cancelMeta);

                                        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                                        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                                        skullMeta.setDisplayName("§e" + plot.getPlayer().getName());
                                        skullMeta.setOwner(plot.getPlayer().getName());
                                        skull.setItemMeta(skullMeta);

                                        p.getInventory().setItem(3, bad);
                                        p.getInventory().setItem(4, good);
                                        p.getInventory().setItem(5, accepted);
                                        p.getInventory().setItem(8, cancel);
                                        p.getInventory().setItem(22, skull);

                                        s.setRate(true);

                                        return;
                                    } else {
                                        Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_notPermittetToEnter, p);
                                        return;
                                    }
                                } else {
                                    if(welt != null) {
                                        p.closeInventory();
                                        p.teleport(plot.getSide());
                                        p.getInventory().clear();

                                        ItemStack bad = new ItemStack(Material.WOOL, 1, (byte) 14);
                                        ItemMeta badMeta = bad.getItemMeta();
                                        badMeta.setDisplayName("§cSchlecht");
                                        bad.setItemMeta(badMeta);

                                        ItemStack good = new ItemStack(Material.WOOL, 1, (byte) 1);
                                        ItemMeta goodMeta = good.getItemMeta();
                                        goodMeta.setDisplayName("§6Weiterbauen");
                                        good.setItemMeta(goodMeta);

                                        ItemStack accepted = new ItemStack(Material.WOOL, 1, (byte) 5);
                                        ItemMeta acceptedMeta = accepted.getItemMeta();
                                        acceptedMeta.setDisplayName("§aAngenommen");
                                        accepted.setItemMeta(acceptedMeta);

                                        ItemStack cancel = new ItemStack(Material.BARRIER);
                                        ItemMeta cancelMeta = cancel.getItemMeta();
                                        cancelMeta.setDisplayName("§cAbbrechen");
                                        cancel.setItemMeta(cancelMeta);

                                        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                                        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                                        skullMeta.setDisplayName("§e" + plot.getPlayer().getName());
                                        skullMeta.setOwner(plot.getPlayer().getName());
                                        skull.setItemMeta(skullMeta);

                                        p.getInventory().setItem(3, bad);
                                        p.getInventory().setItem(4, good);
                                        p.getInventory().setItem(5, accepted);
                                        p.getInventory().setItem(8, cancel);
                                        p.getInventory().setItem(22, skull);

                                        s.setRate(true);

                                        return;
                                    } else {
                                        p.closeInventory();
                                        Messager.toPlayer(MessagerType.COLORED, Message.worldmanagement_teleportFailed, p);
                                        return;
                                    }
                                }

                            } else {
                                p.closeInventory();
                                p.teleport(plot.getSide());
                                p.getInventory().clear();

                                ItemStack bad = new ItemStack(Material.WOOL, 1, (byte) 14);
                                ItemMeta badMeta = bad.getItemMeta();
                                badMeta.setDisplayName("§cSchlecht");
                                bad.setItemMeta(badMeta);

                                ItemStack good = new ItemStack(Material.WOOL, 1, (byte) 1);
                                ItemMeta goodMeta = good.getItemMeta();
                                goodMeta.setDisplayName("§6Weiterbauen");
                                good.setItemMeta(goodMeta);

                                ItemStack accepted = new ItemStack(Material.WOOL, 1, (byte) 5);
                                ItemMeta acceptedMeta = accepted.getItemMeta();
                                acceptedMeta.setDisplayName("§aAngenommen");
                                accepted.setItemMeta(acceptedMeta);

                                ItemStack cancel = new ItemStack(Material.BARRIER);
                                ItemMeta cancelMeta = cancel.getItemMeta();
                                cancelMeta.setDisplayName("§cAbbrechen");
                                cancel.setItemMeta(cancelMeta);

                                ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                                SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                                skullMeta.setDisplayName("§e" + plot.getPlayer().getName());
                                skullMeta.setOwner(plot.getPlayer().getName());
                                skull.setItemMeta(skullMeta);

                                p.getInventory().setItem(3, bad);
                                p.getInventory().setItem(4, good);
                                p.getInventory().setItem(5, accepted);
                                p.getInventory().setItem(8, cancel);
                                p.getInventory().setItem(22, skull);

                                s.setRate(true);

                                return;
                            }
                        } else {
                            p.closeInventory();
                            Messager.toPlayer(MessagerType.COLORED, Message.plotmanager_alreadyRating, p);
                            return;
                        }
                    }
                }

            }
        }

        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
            return;
        } else {

            /*
             *  ContrayBuild - ItemEdit
             */

            if (invname.equals("§cContrayBuild - ItemEdit")) {
                e.setCancelled(true);

                if (e.getClickedInventory().getType() == InventoryType.PLAYER) {
                    ItemStack item = new ItemStack(e.getCurrentItem().getType());
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName("§e" + item.getType().name());
                    item.setItemMeta(meta);
                    INV_BuildUtils.openItemManagementEdit(p, item);
                }


                if (itemname != null) {

                    if (itemname.equals("§aBestätigen")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            ItemManager.registerItem(e.getInventory().getItem(2).getTypeId());
                            INV_BuildUtils.openItemManagementEdit(p, null);
                            return;
                        }
                    }

                    if (itemname.equals("§cAbbrechen")) {
                        if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.items", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                            INV_BuildUtils.openItemManagement(p, 1);
                            return;
                        }
                    }

                }
            }

        }
    }

    public static AnvilGUI.AnvilClickEventHandler anvilHandler(Player p, int zweck, Object ...objs) {

        AnvilGUI.AnvilClickEventHandler handler = new AnvilGUI.AnvilClickEventHandler() {
            @Override
            public void onAnvilClick(AnvilGUI.AnvilClickEvent e) {

                e.setWillDestroy(false);
                e.setWillClose(false);

                if(zweck == 1) {
                    if (e.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                        if (e.getName() != null && e.getName() != "" && e.getName().length() <= 20 && !e.getName().contains(" ") && Bukkit.getOfflinePlayer(e.getName()) != null) {

                            if (Utils.playerExists(Bukkit.getOfflinePlayer(e.getName()))) {
                                if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.player", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                                    if (Bukkit.getOfflinePlayer(e.getName()).isOnline() && Bukkit.getPlayer(e.getName()) != null && SpielerManager.getSpieler(Bukkit.getPlayer(e.getName())) != null) {
                                        INV_BuildUtils.openPlayerManagementSettings(p, SpielerManager.getSpieler(Bukkit.getPlayer(e.getName())));
                                    } else {
                                        INV_BuildUtils.openPlayerManagementSettings(p, Bukkit.getOfflinePlayer(e.getName()));
                                    }
                                }
                                return;
                            } else {
                                Messager.toPlayer(MessagerType.COLORED, Message.mysql_playerDoesntExist, p);
                                if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.player", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                                    INV_BuildUtils.openPlayerManagement(p, 1);
                                }
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                } else if( zweck == 2) {

                    if (e.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                        if (e.getName() != null && e.getName() != "" && e.getName().length() <= 20 && !e.getName().contains(" ") && Bukkit.getOfflinePlayer(e.getName()) != null) {

                            if(WeltenManager.getWelt(((Welt) objs[0]).getName()) != null && Bukkit.getWorld(((Welt) objs[0]).getName()) != null) {
                                if (Utils.playerExists(Bukkit.getOfflinePlayer(e.getName()))) {
                                    if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                                        OfflinePlayer op = Bukkit.getOfflinePlayer(e.getName());
                                        List<OfflinePlayer> list = ((Welt) objs[0]).getBuilders();

                                        if(!list.contains(op)) {
                                            list.add(op);
                                            ((Welt) objs[0]).setBuilders(list);
                                        }
                                        INV_BuildUtils.openWorldManagementBuilders(p, ((Welt) objs[0]), 1);
                                    }
                                    return;
                                } else {
                                    Messager.toPlayer(MessagerType.COLORED, Message.mysql_playerDoesntExist, p);
                                    if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                                        INV_BuildUtils.openWorldManagementBuilders(p, ((Welt) objs[0]), 1);
                                    }
                                    return;
                                }
                            } else {
                                if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                                    INV_BuildUtils.openWorldManagement(p, 1);
                                }
                                return;
                            }
                        }
                    } else {
                        return;
                    }

                } else if( zweck == 3) {
                    if (e.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                        if (e.getName() != null && e.getName() != "" && e.getName().length() <= 20 && !e.getName().contains(" ") && Bukkit.getOfflinePlayer(e.getName()) != null) {

                            if(WeltenManager.getWelt(((Welt) objs[0]).getName()) != null && Bukkit.getWorld(((Welt) objs[0]).getName()) != null) {
                                if (Utils.playerExists(Bukkit.getOfflinePlayer(e.getName()))) {
                                    if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                                        OfflinePlayer op = Bukkit.getOfflinePlayer(e.getName());
                                        List<OfflinePlayer> list = ((Welt) objs[0]).getCustomers();

                                        if(!list.contains(op)) {
                                            list.add(op);
                                            ((Welt) objs[0]).setCustomers(list);
                                        }
                                        INV_BuildUtils.openWorldManagementCustomers(p, ((Welt) objs[0]), 1);
                                    }
                                    return;
                                } else {
                                    Messager.toPlayer(MessagerType.COLORED, Message.mysql_playerDoesntExist, p);
                                    if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                                        INV_BuildUtils.openWorldManagementCustomers(p, ((Welt) objs[0]), 1);
                                    }
                                    return;
                                }
                            } else {
                                if (PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.worlds", Converter.stringToArray("contray.*", "contray.buildutils.*", "contray.buildutils.admin.*"), true, true))) {
                                    INV_BuildUtils.openWorldManagement(p, 1);
                                }
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                }

            }
        };

        return handler;

    }

}
