package de.Fabtopf.BuildUtils.Commands.Inventory;

import de.Fabtopf.BuildUtils.API.*;
import de.Fabtopf.BuildUtils.API.Manager.*;
import de.Fabtopf.BuildUtils.Listener.SERVER_InventoryInteract;
import de.Fabtopf.BuildUtils.Utilities.MySQL.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabi on 20.08.2017.
 */
public class INV_BuildUtils {

    public static void openGUI(Player p) {

        int invsize = 45;
        Inventory inv = Bukkit.createInventory(null, invsize, "§cContrayBuild - Management");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item;
        ItemMeta meta;

        for(int i = 0; i < invsize; i++) {

            if(PermissionManager.check(p, new CustomPerm("contray.buildutils.admin.*", Converter.stringToArray("contray.buildutils.admin.spieler", "contray.buildutils.admin.worlds", "contray.buildutils.admin.settings", "contray.buildutils.admin.module", "contray.buildutils.admin.items", "contray.buildutils.*", "contray.*"), true, true))) {
                switch (i) {

                    case 11:
                        item = new ItemStack(Material.EMPTY_MAP);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§e§lWorld-Management");
                        item.setItemMeta(meta);
                        break;
                    case 14:
                        item = new ItemStack(Material.WORKBENCH);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§e§lInventar-Einstellungen");
                        meta.setLore(Converter.stringToList("§4Achtung!", "§cEinstellungen werden", "§cnicht gespeichert!"));
                        item.setItemMeta(meta);
                        break;
                    case 15:
                        item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§a§lSpieler-Management");
                        item.setItemMeta(meta);
                        break;
                    case 19:
                        item = new ItemStack(Material.REDSTONE_TORCH_ON, 1, (byte) 3);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§c§lPlugin-Settings");
                        item.setItemMeta(meta);
                        break;
                    case 22:
                        item = new ItemStack(Material.NETHER_STAR);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§6§lModule-Settings");
                        item.setItemMeta(meta);
                        break;
                    case 25:
                        item = new ItemStack(Material.ENDER_PEARL);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§5§lWelten besuchen");
                        item.setItemMeta(meta);
                        break;
                    case 29:
                        item = new ItemStack(Material.PAPER);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§c§lItem-Management");
                        item.setItemMeta(meta);
                        break;
                    case 33:
                        if (Bukkit.getPluginManager().isPluginEnabled("PlotSquared")) {
                            item = new ItemStack(Material.GRASS);
                            meta = item.getItemMeta();
                            meta.setDisplayName("§9§lFertige Plots");
                            item.setItemMeta(meta);
                        } else {
                            item = new ItemStack(Material.BARRIER);
                            meta = item.getItemMeta();
                            meta.setDisplayName("§4§lNicht verfügbar!");
                            meta.setLore(Converter.stringToList("§cBitte aktiviere das Plugin", "§cPlotSquared um diese", "§cFunktion freizuschalten!"));
                            item.setItemMeta(meta);
                        }
                        break;

                    default:
                        item = filler;
                        break;

                }
            } else {
                switch (i) {

                    case 13:
                        if(PermissionManager.check(p, new CustomPerm("contray.buildutils.plotrate", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                            if (Bukkit.getPluginManager().isPluginEnabled("PlotSquared")) {
                                item = new ItemStack(Material.GRASS);
                                meta = item.getItemMeta();
                                meta.setDisplayName("§9§lFertige Plots");
                                item.setItemMeta(meta);
                            } else {
                                item = new ItemStack(Material.BARRIER);
                                meta = item.getItemMeta();
                                meta.setDisplayName("§4§lNicht verfügbar!");
                                meta.setLore(Converter.stringToList("§cBitte aktiviere das Plugin", "§cPlotSquared um diese", "§cFunktion freizuschalten!"));
                                item.setItemMeta(meta);
                            }
                        } else {
                            item = filler;
                        }
                        break;
                    case 20:
                        item = new ItemStack(Material.ENDER_PEARL);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§5§lWelten besuchen");
                        item.setItemMeta(meta);
                        break;
                    case 24:
                        if (Bukkit.getPluginManager().isPluginEnabled("PlotSquared")) {
                            item = new ItemStack(Material.GRASS);
                            meta = item.getItemMeta();
                            meta.setDisplayName("§9§lPlot abgeben");
                            item.setItemMeta(meta);
                        } else {
                            item = new ItemStack(Material.BARRIER);
                            meta = item.getItemMeta();
                            meta.setDisplayName("§4§lNicht verfügbar!");
                            item.setItemMeta(meta);
                        }
                        break;
                    case 31:
                        item = new ItemStack(Material.WORKBENCH);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§e§lInventar-Einstellungen");
                        meta.setLore(Converter.stringToList("§4Achtung!", "§cEinstellungen werden", "§cnicht gespeichert!"));
                        item.setItemMeta(meta);
                        break;

                    default:
                        item = filler;
                        break;

                }
            }

            inv.setItem(i, item);
        }

        p.openInventory(inv);

    }

    public static void openWorldManagement(Player p, int page) {
        for(World world : Bukkit.getWorlds()) { WeltenManager.registerWelt(world.getName()); }

        List<Welt> welten = WeltenManager.getWeltenList();

        for(Welt w : welten) {
            if(Bukkit.getWorld(w.getName()) == null) {
                WeltenManager.unregisterWelt(w.getName());
                welten.remove(w);
            }
        }

        int invsize = 0;
        if(welten.size() - ((page - 1) * 36) >= 36) invsize = 54;
        if(welten.size() == 0) invsize = 9;
        if(welten.size() - ((page - 1) * 36) < 36 && welten.size() > 0) invsize = ((int) (welten.size() - (((page - 1) * 36)) + 8) / 9) * 9 + 18;

        Inventory inv = Bukkit.createInventory(null, invsize, "§cContrayBuild - Welten");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        for(int i = 0; i < invsize; i++) {
            if(i + ((page - 1) * 36) < welten.size() && i < 36) {
                item = new ItemStack(Material.GRASS);
                meta = item.getItemMeta();
                meta.setDisplayName("§e" + welten.get(i + (36 * (page - 1))).getName());
                item.setItemMeta(meta);
            } else if(i == invsize - 1 || i == invsize - 9 || (page - 1 > 0 && i == invsize - 6) || (welten.size() - (36 * page) > 0 && i == invsize - 4)) {
                if(i == invsize - 9) {
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                } else if(i == invsize - 1) {
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                } else if(i == invsize - 6) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page - 1));
                    skull.setOwner("MHF_ArrowLeft");
                    item.setItemMeta(skull);
                } else if(i == invsize - 4) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page + 1));
                    skull.setOwner("MHF_ArrowRight");
                    item.setItemMeta(skull);
                }
            } else {
                item = filler;
            }

            inv.setItem(i, item);

        }

        p.openInventory(inv);

    }

    public static void openPlayerManagement(Player p, int page) {

        List<Spieler> spielerlist = SpielerManager.getSpielerList();
        int invsize = 0;
        if(spielerlist.size() - ((page - 1) * 36) >= 36) invsize = 54;
        if(spielerlist.size() == 0) invsize = 9;
        if(spielerlist.size() - ((page - 1) * 36) < 36 && spielerlist.size() > 0) invsize = ((int) (spielerlist.size() - (((page - 1) * 36)) + 8) / 9) * 9 + 18;

        Inventory inv = Bukkit.createInventory(null, invsize, "§cContrayBuild - SpielerSettings");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        for(int i = 0; i < invsize; i++) {

            if(i + ((page - 1) * 36) < spielerlist.size() && i < 36) {
                item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                SkullMeta skull = (SkullMeta) item.getItemMeta();
                skull.setDisplayName("§a" + spielerlist.get(i).getP().getName());
                skull.setOwner(spielerlist.get(i).getP().getName());
                item.setItemMeta(skull);
            } else if(i == invsize - 1 || i == invsize - 9 || i == invsize - 5 || (page - 1 > 0 && i == invsize - 6) || (spielerlist.size() - (36 * page) > 0 && i == invsize - 4)) {
                if(i == invsize - 9) {
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                } else if(i == invsize - 1) {
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                } else if(i == invsize - 6) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page - 1));
                    skull.setOwner("MHF_ArrowLeft");
                    item.setItemMeta(skull);
                } else if(i == invsize - 4) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page + 1));
                    skull.setOwner("MHF_ArrowRight");
                    item.setItemMeta(skull);
                } else if(i == invsize - 5) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§9Offline Spieler");
                    skull.setOwner("MHF_Question");
                    item.setItemMeta(skull);
                }
            } else {
                item = filler;
            }

            inv.setItem(i, item);

        }

        p.openInventory(inv);

    }

    public static void openModuleManagement(Player p, int page) {

        List<Module> modules = ModuleManager.getModules();
        int invsize = 0;
        if(modules.size() - ((page - 1) * 36) >= 36) invsize = 54;
        if(modules.size() == 0) invsize = 9;
        if(modules.size() - ((page - 1) * 36) < 36 && modules.size() > 0) invsize = ((int) (modules.size() - (((page - 1) * 36)) + 8) / 9) * 9 + 18;

        Inventory inv = Bukkit.createInventory(null, invsize, "§cContrayBuild - Modules");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        for(int i = 0; i < invsize; i++) {
            if(i + ((page - 1) * 36) < modules.size() && i < 36) {
                if(modules.get(i + (36 * (page -1))).isEnabled()) {
                    item = new ItemStack(Material.WOOL, 1, (byte) 5);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§a" + modules.get(i + (36 * (page - 1))).getName());
                    item.setItemMeta(meta);
                } else {
                    item = new ItemStack(Material.WOOL, 1, (byte) 14);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§c" + modules.get(i + (36 * (page - 1))).getName());
                    item.setItemMeta(meta);
                }
            } else if(i == invsize - 1 || i == invsize - 9 || (page - 1 > 0 && i == invsize - 6) || (modules.size() - (36 * page) > 0 && i == invsize - 4)) {
                if(i == invsize - 9) {
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                } else if(i == invsize - 1) {
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                } else if(i == invsize - 6) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page - 1));
                    skull.setOwner("MHF_ArrowLeft");
                    item.setItemMeta(skull);
                } else if(i == invsize - 4) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page + 1));
                    skull.setOwner("MHF_ArrowRight");
                    item.setItemMeta(skull);
                }
            } else {
                item = filler;
            }

            inv.setItem(i, item);

        }

        p.openInventory(inv);

    }

    public static void openItemManagement(Player p, int page) {

        List<Item> items = ItemManager.getItems();
        int invsize = 0;
        if(items.size() - ((page - 1) * 36) >= 36) invsize = 54;
        if(items.size() == 0) invsize = 9;
        if(items.size() - ((page - 1) * 36) < 36 && items.size() > 0) invsize = ((int) (items.size() - (((page - 1) * 36)) + 8) / 9) * 9 + 18;

        Inventory inv = Bukkit.createInventory(null, invsize, "§cContrayBuild - Items");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        for(int i = 0; i < invsize; i++) {
            if(i + ((page - 1) * 36) < items.size() && i < 36) {
                item = new ItemStack(Material.getMaterial(items.get(i + (36 * (page - 1))).getId()));
                meta = item.getItemMeta();
                meta.setDisplayName("§e" + Material.getMaterial(items.get(i + (36 * (page - 1))).getId()).name());
                item.setItemMeta(meta);
            } else if(i == invsize - 1 || i == invsize - 9 || i == invsize - 5 || (page - 1 > 0 && i == invsize - 6) || (items.size() - (36 * page) > 0 && i == invsize - 4)) {
                if(i == invsize - 9) {
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                } else if(i == invsize - 1) {
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                } else if(i == invsize - 6) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page - 1));
                    skull.setOwner("MHF_ArrowLeft");
                    item.setItemMeta(skull);
                } else if(i == invsize - 4) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page + 1));
                    skull.setOwner("MHF_ArrowRight");
                    item.setItemMeta(skull);
                } else if(i == invsize - 5) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§9Liste editieren");
                    skull.setOwner("MHF_Question");
                    item.setItemMeta(skull);
                }
            } else {
                item = filler;
            }

            inv.setItem(i, item);

        }

        p.openInventory(inv);

    }

    public static void openPlotManagement(Player p, int page) {

        List<FinishedPlot> plots = new ArrayList<FinishedPlot>();

        for(FinishedPlot plot : PlotManager.getPlots()) {
            if(plot.getPlotState().getRated() == false) {
                plots.add(plot);
            }
        }

        int invsize = 0;
        if(plots.size() - ((page - 1) * 36) >= 36) invsize = 54;
        if(plots.size() == 0) invsize = 9;
        if(plots.size() - ((page - 1) * 36) < 36 && plots.size() > 0) invsize = ((int) (plots.size() - (((page - 1) * 36)) + 8) / 9) * 9 + 18;

        Inventory inv = Bukkit.createInventory(null, invsize, "§cContrayBuild - Plots");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        for(int i = 0; i < invsize; i++) {
            if(i + ((page - 1) * 36) < plots.size() && i < 36) {
                item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                SkullMeta skull = (SkullMeta) item.getItemMeta();
                skull.setDisplayName("§e" + plots.get(i).getPlayer().getName());
                skull.setOwner(plots.get(i).getPlayer().getName());
                item.setItemMeta(skull);
            } else if(i == invsize - 1 || i == invsize - 9 || (page - 1 > 0 && i == invsize - 6) || (plots.size() - (36 * page) > 0 && i == invsize - 4)) {
                if(i == invsize - 9) {
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                } else if(i == invsize - 1) {
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                } else if(i == invsize - 6) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page - 1));
                    skull.setOwner("MHF_ArrowLeft");
                    item.setItemMeta(skull);
                } else if(i == invsize - 4) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page + 1));
                    skull.setOwner("MHF_ArrowRight");
                    item.setItemMeta(skull);
                }
            } else {
                item = filler;
            }

            inv.setItem(i, item);

        }

        p.openInventory(inv);

    }

    public static void openItemManagementEdit(Player p, ItemStack i) {

        Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, "§cContrayBuild - ItemEdit");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack icontinue = new ItemStack(Material.WOOL, 1, (byte) 5);
        ItemMeta continueMeta = icontinue.getItemMeta();
        continueMeta.setDisplayName("§aBestätigen");
        icontinue.setItemMeta(continueMeta);

        ItemStack cancel = new ItemStack(Material.WOOL, 1, (byte) 14);
        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.setDisplayName("§cAbbrechen");
        cancel.setItemMeta(cancelMeta);

        inv.setItem(0, filler);
        if(i != null) inv.setItem(0, icontinue);
        inv.setItem(4, cancel);
        inv.setItem(1, filler);
        inv.setItem(3, filler);
        if(i != null) inv.setItem(2, i);

        p.openInventory(inv);

    }

    public static void openItemManagementSettings(Player p, Item i) {

        Inventory inv = Bukkit.createInventory(null, 45, "§cContrayBuild - ItemSettings");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        for(int d = 0; d < 45; d++) {
            switch(d) {

                case 13:
                    item = new ItemStack(Material.getMaterial(i.getId()));
                    meta = item.getItemMeta();
                    meta.setDisplayName("§e" + item.getType().name());
                    item.setItemMeta(meta);
                    break;

                case 28:
                    if(i.isFillBucket()) {
                        item = new ItemStack(Material.WOOL, 1, (byte) 5);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§aEimer befüllen");
                        meta.setLore(Converter.stringToList("§cNur für Eimer"));
                        item.setItemMeta(meta);
                    } else {
                        item = new ItemStack(Material.WOOL, 1, (byte) 14);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§cEimer befüllen");
                        meta.setLore(Converter.stringToList("§cNur für Eimer"));
                        item.setItemMeta(meta);
                    }
                    break;
                case 29:
                    if(i.isEmptyBucket()) {
                        item = new ItemStack(Material.WOOL, 1, (byte) 5);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§aEimer entleeren");
                        meta.setLore(Converter.stringToList("§cNur für Eimer"));
                        item.setItemMeta(meta);
                    } else {
                        item = new ItemStack(Material.WOOL, 1, (byte) 14);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§cEimer entleeren");
                        meta.setLore(Converter.stringToList("§cNur für Eimer"));
                        item.setItemMeta(meta);
                    }
                    break;
                case 32:
                    if(i.isInteract()) {
                        item = new ItemStack(Material.WOOL, 1, (byte) 5);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§aInteraktion");
                        item.setItemMeta(meta);
                    } else {
                        item = new ItemStack(Material.WOOL, 1, (byte) 14);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§cInteraktion");
                        item.setItemMeta(meta);
                    }
                    break;
                case 33:
                    if(i.isInventory()) {
                        item = new ItemStack(Material.WOOL, 1, (byte) 5);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§aInventar");
                        meta.setLore(Converter.stringToList("§4Achtung!", "§cVerbietet nur das Bewegen", "§cdes Items im Inventar!"));
                        item.setItemMeta(meta);
                    } else {
                        item = new ItemStack(Material.WOOL, 1, (byte) 14);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§cInventar");
                        meta.setLore(Converter.stringToList("§4Achtung!", "§cVerbietet nur das Bewegen", "§cdes Items im Inventar!"));
                        item.setItemMeta(meta);
                    }
                    break;
                case 34:
                    if(i.isPlace()) {
                        item = new ItemStack(Material.WOOL, 1, (byte) 5);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§aPlatzieren");
                        item.setItemMeta(meta);
                    } else {
                        item = new ItemStack(Material.WOOL, 1, (byte) 14);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§cPlatzieren");
                        item.setItemMeta(meta);
                    }
                    break;
                case 36:
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                    break;
                case 44:
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                    break;

                default:
                    item = filler;
                    break;

            }

            inv.setItem(d, item);
        }

        p.openInventory(inv);
    }

    public static void openWorldManagementSettings(Player p, Welt welt) {

        Inventory inv = Bukkit.createInventory(null, 45, "§cContrayBuild - WeltenSettings");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        for(int i = 0; i < 45; i++) {
            switch(i) {

                case 13:
                    item = new ItemStack(Material.GRASS);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§e" + welt.getName());
                    item.setItemMeta(meta);
                    break;

                case 28:
                    item = new ItemStack(Material.WOOL, 1, (byte) (welt.isManaged() ? 5 : 14));
                    meta = item.getItemMeta();
                    meta.setDisplayName((welt.isManaged() ? "§a" : "§c") + "Managed");
                    item.setItemMeta(meta);
                    break;
                case 30:
                    item = new ItemStack(Material.WOOL, 1, (byte) (welt.isLobby() ? 5 : 8));
                    meta = item.getItemMeta();
                    meta.setDisplayName((welt.isLobby() ? "§a" : "§c") + "Lobby");
                    item.setItemMeta(meta);
                    break;
                case 31:
                    item = new ItemStack(Material.WOOL, 1, (byte) (welt.isOpen() ? 5 : 8));
                    meta = item.getItemMeta();
                    meta.setDisplayName((welt.isOpen() ? "§a" : "§c") + "Öffentlich");
                    meta.setLore(Converter.stringToList("§cLinks: Modus welchseln", "§cMitte: Settings"));
                    item.setItemMeta(meta);
                    break;
                case 33:
                    item = new ItemStack(Material.WOOL, 1, (byte) 4);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§eBuilders");
                    item.setItemMeta(meta);
                    break;
                case 34:
                    item = new ItemStack(Material.WOOL, 1, (byte) 9);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§3Besucher");
                    item.setItemMeta(meta);
                    break;
                case 36:
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                    break;
                case 44:
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                    break;

                default:
                    item = filler;
                    break;
            }

            inv.setItem(i, item);
        }

        p.openInventory(inv);

    }

    public static void openWorldManagementGradeSettings(Player p, Welt welt) {

        Inventory inv = Bukkit.createInventory(null, 45, "§cContrayBuild - WeltStufe");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        for(int i = 0; i < 45; i++) {
            switch(i) {

                case 13:
                    item = new ItemStack(Material.GRASS);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§e" + welt.getName());
                    item.setItemMeta(meta);
                    break;

                case 29:
                    if(welt.getNeededGrade() > 0) {
                        item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                        SkullMeta skull1 = (SkullMeta) item.getItemMeta();
                        skull1.setDisplayName("§cStufe senken");
                        skull1.setOwner("MHF_ArrowDown");
                        item.setItemMeta(skull1);
                    } else {
                        item = filler;
                    }
                    break;
                case 31:
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull2 = (SkullMeta) item.getItemMeta();
                    skull2.setDisplayName("§cStufe " + welt.getNeededGrade());
                    skull2.setOwner("MHF_Question");
                    item.setItemMeta(skull2);
                    break;
                case 33:
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull3 = (SkullMeta) item.getItemMeta();
                    skull3.setDisplayName("§cStufe erhöhen");
                    skull3.setOwner("MHF_ArrowUp");
                    item.setItemMeta(skull3);
                    break;
                case 36:
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                    break;
                case 44:
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                    break;

                default:
                    item = filler;
                    break;

            }

            inv.setItem(i, item);
        }

        p.openInventory(inv);

    }

    public static void openPlayerManagementEdit(Player p) {

        AnvilGUI anvil = new AnvilGUI(p, SERVER_InventoryInteract.anvilHandler(p, 1, null));

        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName("§9Bitte gebe einen Spielernamen ein!");
        item.setItemMeta(skull);

        anvil.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, item);

        anvil.open();

    }

    public static void openWorldManagementBuildersEdit(Player p, Welt w) {

        AnvilGUI anvil = new AnvilGUI(p, SERVER_InventoryInteract.anvilHandler(p, 2, w));

        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName("§9Bitte gebe einen Spielernamen ein!");
        item.setItemMeta(skull);

        anvil.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, item);

        anvil.open();

    }

    public static void openWorldManagementCustomersEdit(Player p, Welt w) {

        AnvilGUI anvil = new AnvilGUI(p, SERVER_InventoryInteract.anvilHandler(p, 3, w));

        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName("§9Bitte gebe einen Spielernamen ein!");
        item.setItemMeta(skull);

        anvil.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, item);

        anvil.open();

    }

    public static void openPlayerManagementSettings(Player p, Spieler s) {

        Inventory inv = Bukkit.createInventory(null, 45, "§cContrayBuild - SpielerStufe");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        for(int i = 0; i < 45; i++) {
            switch (i) {

                case 13:
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull1 = (SkullMeta) item.getItemMeta();
                    skull1.setDisplayName("§e" + s.getP().getName());
                    skull1.setOwner(s.getP().getName());
                    item.setItemMeta(skull1);
                    break;

                case 29:
                    if(s.getBuilderGrade() > 0) {
                        item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                        SkullMeta skull2 = (SkullMeta) item.getItemMeta();
                        skull2.setDisplayName("§cStufe senken");
                        skull2.setOwner("MHF_ArrowDown");
                        item.setItemMeta(skull2);
                    } else {
                        item = filler;
                    }
                    break;
                case 31:
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull3 = (SkullMeta) item.getItemMeta();
                    skull3.setDisplayName("§cStufe " + s.getBuilderGrade());
                    skull3.setOwner("MHF_Question");
                    item.setItemMeta(skull3);
                    break;
                case 33:
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull4 = (SkullMeta) item.getItemMeta();
                    skull4.setDisplayName("§cStufe erhöhen");
                    skull4.setOwner("MHF_ArrowUp");
                    item.setItemMeta(skull4);
                    break;
                case 36:
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                    break;
                case 44:
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                    break;

                default:
                    item = filler;
                    break;

            }

            inv.setItem(i, item);

        }

        p.openInventory(inv);

    }

    public static void openPlayerManagementSettings(Player p, OfflinePlayer op) {

        Inventory inv = Bukkit.createInventory(null, 45, "§cContrayBuild - SpielerStufe");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        int grade = Utils.getBuilderGrade(Utils.getPlayerID(op));

        for(int i = 0; i < 45; i++) {
            switch (i) {

                case 13:
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull1 = (SkullMeta) item.getItemMeta();
                    skull1.setDisplayName("§e" + op.getName());
                    skull1.setOwner(op.getName());
                    item.setItemMeta(skull1);
                    break;

                case 29:
                    if(grade > 0) {
                        item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                        SkullMeta skull2 = (SkullMeta) item.getItemMeta();
                        skull2.setDisplayName("§cStufe senken");
                        skull2.setOwner("MHF_ArrowDown");
                        item.setItemMeta(skull2);
                    } else {
                        item = filler;
                    }
                    break;
                case 31:
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull3 = (SkullMeta) item.getItemMeta();
                    skull3.setDisplayName("§cStufe " + grade);
                    skull3.setOwner("MHF_Question");
                    item.setItemMeta(skull3);
                    break;
                case 33:
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull4 = (SkullMeta) item.getItemMeta();
                    skull4.setDisplayName("§cStufe erhöhen");
                    skull4.setOwner("MHF_ArrowUp");
                    item.setItemMeta(skull4);
                    break;
                case 36:
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                    break;
                case 44:
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                    break;

                default:
                    item = filler;
                    break;

            }

            inv.setItem(i, item);

        }

        p.openInventory(inv);

    }

    public static void openWorldVisit(Player p, int page) {

        List<Welt> welten = new ArrayList<Welt>();

        for(World w : Bukkit.getWorlds()) {
            WeltenManager.registerWelt(w.getName());
        }

        for( Welt w : WeltenManager.getWeltenList()) {
            if(w.getBuilders().contains(Bukkit.getOfflinePlayer(p.getUniqueId())) || w.getCustomers().contains(Bukkit.getOfflinePlayer(p.getUniqueId()))) {
                if(!welten.contains(w) && Bukkit.getWorld(w.getName()) != null) {
                    welten.add(w);
                }
            }
        }

        int invsize = 0;
        if(welten.size() - ((page - 1) * 36) >= 36) invsize = 54;
        if(welten.size() == 0) invsize = 9;
        if(welten.size() - ((page - 1) * 36) < 36 && welten.size() > 0) invsize = ((int) (welten.size() - (((page - 1) * 36)) + 8) / 9) * 9 + 18;

        Inventory inv = Bukkit.createInventory(null, invsize, "§cContrayBuild - WeltenBesuch");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        for(int i = 0; i < invsize; i++) {
            if(i + ((page - 1) * 36) < welten.size() && i < 36) {
                item = new ItemStack(Material.GRASS);
                meta = item.getItemMeta();
                meta.setDisplayName("§3" + welten.get(i).getName());
                item.setItemMeta(meta);
            } else if(i == invsize - 1 || i == invsize - 9 || i == invsize - 5 || (page - 1 > 0 && i == invsize - 6) || (welten.size() - (36 * page) > 0 && i == invsize - 4)) {
                if(i == invsize - 9) {
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                } else if(i == invsize - 1) {
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                } else if(i == invsize - 6) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page - 1));
                    skull.setOwner("MHF_ArrowLeft");
                    item.setItemMeta(skull);
                } else if(i == invsize - 4) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page + 1));
                    skull.setOwner("MHF_ArrowRight");
                    item.setItemMeta(skull);
                }
            } else {
                item = filler;
            }

            inv.setItem(i, item);

        }

        p.openInventory(inv);

    }

    public static void openWorldManagementBuilders(Player p, Welt w, int page) {

        List<Spieler> spielerlist = new ArrayList<Spieler>();

        for( Spieler s : SpielerManager.getSpielerList()) {
            if(w.getBuilders().contains(s.getOp())) {
                if(!spielerlist.contains(s)) {
                    spielerlist.add(s);
                }
            }
        }

        int invsize = 0;
        if(spielerlist.size() - ((page - 1) * 36) >= 36) invsize = 54;
        if(spielerlist.size() == 0) invsize = 9;
        if(spielerlist.size() - ((page - 1) * 36) < 36 && spielerlist.size() > 0) invsize = ((int) (spielerlist.size() - (((page - 1) * 36)) + 8) / 9) * 9 + 18;

        Inventory inv = Bukkit.createInventory(null, invsize, "§cContrayBuild - WeltenBuilders");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        for(int i = 0; i < invsize; i++) {
            if(i + ((page - 1) * 36) < spielerlist.size() && i < 36) {
                item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                SkullMeta skull = (SkullMeta) item.getItemMeta();
                skull.setDisplayName("§a" + spielerlist.get(i).getP().getName());
                skull.setOwner(spielerlist.get(i).getP().getName());
                item.setItemMeta(skull);
            } else if(i == invsize - 1 || i == invsize - 9 || i == invsize - 8 || i == invsize - 5 || (page - 1 > 0 && i == invsize - 6) || (spielerlist.size() - (36 * page) > 0 && i == invsize - 4)) {
                if(i == invsize - 9) {
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                } else if(i == invsize - 1) {
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                } else if(i == invsize - 6) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page - 1));
                    skull.setOwner("MHF_ArrowLeft");
                    item.setItemMeta(skull);
                } else if(i == invsize - 4) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page + 1));
                    skull.setOwner("MHF_ArrowRight");
                    item.setItemMeta(skull);
                } else if(i == invsize - 5) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§9Liste editieren");
                    skull.setOwner("MHF_Question");
                    item.setItemMeta(skull);
                } else if(i == invsize - 8) {
                    item = new ItemStack(Material.GRASS);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§e" + w.getName());
                    item.setItemMeta(meta);
                }
            } else {
                item = filler;
            }

            inv.setItem(i, item);

        }

        p.openInventory(inv);

    }

    public static void openWorldManagementCustomers(Player p, Welt w, int page) {

        List<Spieler> spielerlist = new ArrayList<Spieler>();

        for( Spieler s : SpielerManager.getSpielerList()) {
            if(w.getCustomers().contains(s.getOp())) {
                if(!spielerlist.contains(s)) {
                    spielerlist.add(s);
                }
            }
        }

        int invsize = 0;
        if(spielerlist.size() - ((page - 1) * 36) >= 36) invsize = 54;
        if(spielerlist.size() == 0) invsize = 9;
        if(spielerlist.size() - ((page - 1) * 36) < 36 && spielerlist.size() > 0) invsize = ((int) (spielerlist.size() - (((page - 1) * 36)) + 8) / 9) * 9 + 18;

        Inventory inv = Bukkit.createInventory(null, invsize, "§cContrayBuild - WeltenBesucher");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack item = null;
        ItemMeta meta;

        for(int i = 0; i < invsize; i++) {
            if(i + ((page - 1) * 36) < spielerlist.size() && i < 36) {
                item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                SkullMeta skull = (SkullMeta) item.getItemMeta();
                skull.setDisplayName("§a" + spielerlist.get(i).getP().getName());
                skull.setOwner(spielerlist.get(i).getP().getName());
                item.setItemMeta(skull);
            } else if(i == invsize - 1 || i == invsize - 9 || i == invsize - 8 || i == invsize - 5 || (page - 1 > 0 && i == invsize - 6) || (spielerlist.size() - (36 * page) > 0 && i == invsize - 4)) {
                if(i == invsize - 9) {
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                } else if(i == invsize - 1) {
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                } else if(i == invsize - 6) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page - 1));
                    skull.setOwner("MHF_ArrowLeft");
                    item.setItemMeta(skull);
                } else if(i == invsize - 4) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§eZu Seite " + (page + 1));
                    skull.setOwner("MHF_ArrowRight");
                    item.setItemMeta(skull);
                } else if(i == invsize - 5) {
                    item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skull = (SkullMeta) item.getItemMeta();
                    skull.setDisplayName("§9Liste editieren");
                    skull.setOwner("MHF_Question");
                    item.setItemMeta(skull);
                } else if(i == invsize - 8) {
                    item = new ItemStack(Material.GRASS);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§e" + w.getName());
                    item.setItemMeta(meta);
                }
            } else {
                item = filler;
            }

            inv.setItem(i, item);

        }

        p.openInventory(inv);

    }

    public static void openPluginSettings(Player p) {

    }

    public static void openInventorySettings(Player p) {

        Inventory inv = Bukkit.createInventory(null, 45, "§cContrayBuild - SpecialItems");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack blocked = new ItemStack(Material.BARRIER);
        ItemMeta blockedMeta = blocked.getItemMeta();
        blockedMeta.setDisplayName("§cNicht verfügbar");
        blockedMeta.setLore(Converter.stringToList("§cUm dieses Item nutzen zu", "§ckönnen fehlen dir Berechtigungen!"));
        blocked.setItemMeta(blockedMeta);

        ItemStack item = null;
        ItemMeta meta;

        Spieler s = SpielerManager.getSpieler(p);

        for(int i = 0; i < 45; i++) {

            switch(i) {

                case 10:
                    item = new ItemStack(Material.SUGAR);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§eSpeed");
                    if(s.isInv_speed()) meta.addEnchant(Enchantment.LUCK, 1, true);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    item.setItemMeta(meta);
                    break;
                case 28:
                    item = new ItemStack(Material.FEATHER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§eFlugmodus");
                    if(s.isInv_fly()) meta.addEnchant(Enchantment.LUCK, 1, true);
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    item.setItemMeta(meta);
                    break;
                case 13:
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.worldmanagement.visit", Converter.stringToArray("contray.buildutils.worldmanagement.*", "contray.buildutils.*", "contray.*"), true, true))) {
                        item = new ItemStack(Material.ENDER_PEARL);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§aWelten-Besuch");
                        if(s.isInv_teleport()) meta.addEnchant(Enchantment.LUCK, 1, true);
                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        item.setItemMeta(meta);
                    } else {
                        item = blocked;
                    }
                    break;
                case 31:
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.changeinventory", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                        item = new ItemStack(Material.WORKBENCH);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§aInventar-Settings");
                        if(s.isInv_invsettings()) meta.addEnchant(Enchantment.LUCK, 1, true);
                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        item.setItemMeta(meta);
                    } else {
                        item = blocked;
                    }
                    break;
                case 16:
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.plotfinish", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                        item = new ItemStack(Material.EMERALD);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§ePlot abgeben");
                        if(s.isInv_finish()) meta.addEnchant(Enchantment.LUCK, 1, true);
                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        item.setItemMeta(meta);
                    } else {
                        item = blocked;
                    }
                    break;
                case 34:
                    if(PermissionManager.check(p, new CustomPerm("contray.buildutils.plotrate", Converter.stringToArray("contray.*", "contray.buildutils.*"), true, true))) {
                        item = new ItemStack(Material.PAPER);
                        meta = item.getItemMeta();
                        meta.setDisplayName("§cPlot bewerten");
                        if(s.isInv_rate()) meta.addEnchant(Enchantment.LUCK, 1, true);
                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        item.setItemMeta(meta);
                    } else {
                        item = blocked;
                    }
                    break;
                case 36:
                    item = new ItemStack(Material.EMERALD);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§aReload");
                    item.setItemMeta(meta);
                    break;
                case 44:
                    item = new ItemStack(Material.BARRIER);
                    meta = item.getItemMeta();
                    meta.setDisplayName("§cZurück");
                    item.setItemMeta(meta);
                    break;

                default:
                    item = filler;
                    break;

            }

            inv.setItem(i, item);

        }

        p.openInventory(inv);

    }

    public static void openPlotFinishing(Player p) {

        Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, "§cContrayBuild - PlotAbgabe");

        ItemStack filler = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName("§0");
        filler.setItemMeta(fillerMeta);

        ItemStack icontinue = new ItemStack(Material.WOOL, 1, (byte) 5);
        ItemMeta continueMeta = icontinue.getItemMeta();
        continueMeta.setDisplayName("§aBestätigen");
        icontinue.setItemMeta(continueMeta);

        ItemStack cancel = new ItemStack(Material.WOOL, 1, (byte) 14);
        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.setDisplayName("§cAbbrechen");
        cancel.setItemMeta(cancelMeta);

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setDisplayName("§eMöchstest du diesen Plot wirklich abgeben?");
        skullMeta.setOwner("MHF_Question");
        skull.setItemMeta(skullMeta);

        inv.setItem(0, icontinue);
        inv.setItem(1, filler);
        inv.setItem(2, skull);
        inv.setItem(3, filler);
        inv.setItem(4, cancel);

        p.openInventory(inv);

    }

}
